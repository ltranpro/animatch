package com.animatch.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.animatch.api.model.Anime;
import com.animatch.api.repository.AnimeRepository;
import com.animatch.api.service.AnimeService;
import com.animatch.api.service.JikanService;

@RestController
@RequestMapping("/api/animes")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimeController {

    private final JikanService jikanService;
    private final AnimeRepository animeRepository;
    private final AnimeService animeService; // <--- Ajouté pour le catalogue

    // Un seul constructeur pour injecter toutes les dépendances
    public AnimeController(JikanService jikanService, 
                           AnimeRepository animeRepository, 
                           AnimeService animeService) {
        this.jikanService = jikanService;
        this.animeRepository = animeRepository;
        this.animeService = animeService;
    }

    // Affiche les animés déjà en base (souvent utilisé pour la Home)
    @GetMapping("/top")
    public Page<Anime> getTopAnimes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // On définit le tri par rang ici
        Pageable pageable = PageRequest.of(page, size, Sort.by("rank").ascending());
        
        // On appelle la méthode du repository (assure-toi qu'elle a bien ce nom exact)
        return animeRepository.findAllByRankIsNotNullOrderByRankAsc(pageable);
    }

  // Le catalogue intelligent (Cache + Lettre + Pagination)
    @GetMapping("/catalogue")
    public Page<Anime> getCatalogue(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String letter
    ) {
        // On appelle ton service qui s'occupe de la logique (tri + fetch si besoin)
        return animeService.getCatalogueByLetter(letter, page, size);
    }

    // Route utilitaire pour forcer l'import du Top Anime
    @GetMapping("/fetch")
    public String fetchFromJikan() {
        jikanService.saveTopAnimes();
        return "Base de données mise à jour avec Jikan !";
    }
}