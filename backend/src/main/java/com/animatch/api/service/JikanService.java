package com.animatch.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.animatch.api.dto.JikanResponse;
import com.animatch.api.model.Anime;
import com.animatch.api.repository.AnimeRepository;

@Service
public class JikanService {

    @Autowired
    private AnimeRepository animeRepository;
    
    private final RestTemplate restTemplate;

    public JikanService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Méthode unique pour transformer le DTO en Entité
private Anime mapToEntity(JikanResponse.AnimeData data) {
    Anime anime = new Anime();
    anime.setTitle(data.getTitle());
    anime.setSynopsis(data.getSynopsis());
    anime.setScore(data.getScore());
    anime.setRank(data.getRank());
    anime.setPopularity(data.getPopularity()); // Nouvelle donnée
    
    // Gestion des images (on prend la large pour la fiche détail)
    if (data.getImages() != null && data.getImages().getJpg() != null) {
        anime.setImageUrl(data.getImages().getJpg().getImage_url());
        anime.setLargeImageUrl(data.getImages().getJpg().getLarge_image_url());
    }

    // Infos techniques
    anime.setType(data.getType());
    anime.setSource(data.getSource());
    anime.setEpisodes(data.getEpisodes());
    anime.setStatus(data.getStatus());
    anime.setDuration(data.getDuration());
    anime.setRating(data.getRating());
    anime.setYear(data.getYear());

    // Trailer YouTube (on ne stocke que l'ID pour plus de facilité côté Angular)
    if (data.getTrailer() != null) {
        anime.setTrailerUrl(data.getTrailer().getYoutube_id());
    }

    // Mapping des Genres (List -> String)
    if (data.getGenres() != null) {
        String genresStr = data.getGenres().stream()
            .map(JikanResponse.Genre::getName)
            .collect(java.util.stream.Collectors.joining(", "));
        anime.setGenres(genresStr);
    }

    // Mapping des Studios (List -> String)
    if (data.getStudios() != null) {
        String studiosStr = data.getStudios().stream()
            .map(JikanResponse.Studio::getName)
            .collect(java.util.stream.Collectors.joining(", "));
        anime.setStudios(studiosStr);
    }

    return anime;
}

    public List<Anime> saveTopAnimes() {
        animeRepository.deleteAll(); // Attention: radical si tu as des relations (favoris, etc.)
        List<Anime> allAnimes = new java.util.ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String url = "https://api.jikan.moe/v4/top/anime?page=" + i;
            JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);

            if (response != null && response.getData() != null) {
                allAnimes.addAll(response.getData().stream().map(this::mapToEntity).toList());
            }
            try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        return animeRepository.saveAll(allAnimes);
    }

 public void fetchAndSaveByLetter(String letter) {
    String url = "https://api.jikan.moe/v4/anime?letter=" + letter + "&order_by=title&sort=asc";
    try {
        JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);
        if (response != null && response.getData() != null) {
            
            List<Anime> animesToSave = response.getData().stream()
                .map(this::mapToEntity)
                // FILTRE : On ne garde que ceux dont le titre n'est PAS en base
                .filter(anime -> !animeRepository.existsByTitle(anime.getTitle()))
                .toList();

            if (!animesToSave.isEmpty()) {
                animeRepository.saveAll(animesToSave);
                System.out.println(letter + " : " + animesToSave.size() + " nouveaux animés ajoutés.");
            }
        }
    } catch (Exception e) {
        System.err.println("Erreur Jikan pour la lettre " + letter + " : " + e.getMessage());
    }
}

    // Dans JikanService.java

public void saveFullCatalog() {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    for (char c : alphabet.toCharArray()) {
        String letter = String.valueOf(c);
        System.out.println("Importation des animes commençant par : " + letter);
        
        // On réutilise ta méthode existante
        fetchAndSaveByLetter(letter);
        
        // PAUSE CRUCIALE : Jikan limite le nombre de requêtes. 
        // 1 seconde de pause entre chaque lettre pour éviter le blocage (Error 429).
        try { 
            Thread.sleep(1000); 
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }
}
}