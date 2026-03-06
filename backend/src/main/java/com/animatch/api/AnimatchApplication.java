package com.animatch.api;

import org.springframework.boot.CommandLineRunner; // Vérifie bien ton package service
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.animatch.api.repository.AnimeRepository;
import com.animatch.api.service.JikanService;

@SpringBootApplication
public class AnimatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimatchApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Ce bloc s'exécute AUTOMATIQUEMENT après le démarrage de l'app
 @Bean
CommandLineRunner run(JikanService jikanService, AnimeRepository animeRepository) {
    return args -> {
        // On vérifie si la table est vide
        if (animeRepository.count() == 0) {
            System.out.println("La base est vide. Récupération du Top Jikan...");
            jikanService.saveTopAnimes();
            System.out.println("Synchronisation terminée !");
        } else {
            System.out.println("La base contient déjà " + animeRepository.count() + " animes. Pas besoin de mise à jour.");
        }
    };
}
}