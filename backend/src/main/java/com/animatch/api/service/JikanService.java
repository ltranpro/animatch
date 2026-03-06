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

    private final RestTemplate restTemplate;

    public JikanService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<JikanResponse.AnimeData> getTopAnimes() {
        String url = "https://api.jikan.moe/v4/top/anime";
        JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);
        return response != null ? response.getData() : List.of();
    }

    @Autowired
private AnimeRepository animeRepository;

public List<Anime> saveTopAnimes() {
    // 1. On vide la table pour repartir de zéro
    animeRepository.deleteAll();
    List<Anime> allAnimes = new java.util.ArrayList<>();

    // On boucle pour récupérer les 4 premières pages (4 x 25 = 100)
    for (int i = 1; i <= 4; i++) {
        String url = "https://api.jikan.moe/v4/top/anime?page=" + i;
        JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);

        if (response != null && response.getData() != null) {
            List<Anime> pageAnimes = response.getData().stream().map(data -> {
                Anime anime = new Anime();
                anime.setTitle(data.getTitle());
                anime.setSynopsis(data.getSynopsis());
                if (data.getImages() != null && data.getImages().getJpg() != null) {
                    anime.setImageUrl(data.getImages().getJpg().getImage_url());
                }
                return anime;
            }).toList();
            
            allAnimes.addAll(pageAnimes);
        }
        
        // Petite pause pour respecter l'API Jikan (ils n'aiment pas les requêtes trop rapides)
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    return animeRepository.saveAll(allAnimes);
}
}