package com.animatch.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animatch.api.model.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    // Grâce à JpaRepository, on a déjà .save(), .findAll(), etc.
}

