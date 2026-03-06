package com.animatch.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest; // <--- Importe ton repository
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.animatch.api.model.Anime;
import com.animatch.api.repository.AnimeRepository;
import com.animatch.api.service.JikanService;

@RestController
@RequestMapping("/api/animes")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimeController {

    private final JikanService jikanService;
    private final AnimeRepository animeRepository; // <--- Ajoute le repository

    public AnimeController(JikanService jikanService, AnimeRepository animeRepository) {
        this.jikanService = jikanService;
        this.animeRepository = animeRepository;
    }

    // Nouvelle route pour paginer ce qu'on a en base de données
    @GetMapping("/top")
    public Page<Anime> getTopAnimes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        // On demande au repository de nous donner seulement un morceau de la table
        return animeRepository.findAll(PageRequest.of(page, size));
    }

    // Garde une route spéciale juste pour forcer la mise à jour depuis Jikan si besoin
    @GetMapping("/fetch")
    public String fetchFromJikan() {
        jikanService.saveTopAnimes();
        return "Base de données mise à jour avec Jikan !";
    }
}