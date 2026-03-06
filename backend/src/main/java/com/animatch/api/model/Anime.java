package com.animatch.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animes")
@Data // <-- C'est cette ligne qui crée automatiquement le setSynopsis()
@NoArgsConstructor
@AllArgsConstructor
public class Anime {
    
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String synopsis;

    private String imageUrl;
    private String largeImageUrl; // Pour le fond de la page détails

    private Double score;
    private Integer rank;
    private Integer popularity; // Nouveau : utile pour le tri

    private String type;        // TV, Movie, etc.
    private String source;      // Manga, Original, etc.
    private Integer episodes;
    private String status;      // Finished Airing, etc.
    private String duration;
    private String rating;      // PG-13, etc.
    private Integer year;

    private String trailerUrl;  // ID YouTube pour l'embed
    
    @Column(columnDefinition = "TEXT")
    private String genres;      // On stockera "Action, Fantasy"

    @Column(columnDefinition = "TEXT")
    private String studios;     // On stockera "MAPPA, Madhouse"
}