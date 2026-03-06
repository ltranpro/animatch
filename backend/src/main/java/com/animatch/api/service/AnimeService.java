package com.animatch.api.service; // Doit correspondre au dossier

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.animatch.api.model.Anime;
import com.animatch.api.repository.AnimeRepository;

@Service
public class AnimeService { // La classe DOIT être déclarée ici

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private JikanService jikanService;

  public Page<Anime> getCatalogueByLetter(String letter, int page, int size) {
    // On peut aussi forcer le tri ici dans le Pageable par sécurité
    Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
    
    // Utilisation de la méthode triée du repository
    Page<Anime> localResults = animeRepository.findByTitleStartingWithIgnoreCaseOrderByTitleAsc(letter, pageable);

    if (localResults.isEmpty()) {
        jikanService.fetchAndSaveByLetter(letter); 
        return animeRepository.findByTitleStartingWithIgnoreCaseOrderByTitleAsc(letter, pageable);
    }

    return localResults;
}

    
    
}