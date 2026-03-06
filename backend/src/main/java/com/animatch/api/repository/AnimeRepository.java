package com.animatch.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Vérifie cet import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animatch.api.model.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    boolean existsByTitle(String title);

    // Ajout de "OrderByTitleAsc" pour le catalogue
    Page<Anime> findByTitleStartingWithIgnoreCaseOrderByTitleAsc(String letter, Pageable pageable);

    // Ajout de "OrderByRankAsc" pour le top
    Page<Anime> findAllByRankIsNotNullOrderByRankAsc(Pageable pageable);
}