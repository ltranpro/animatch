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
    
    @Column(unique = true)
    private String title;

    @Column(columnDefinition = "TEXT") // Pour accepter les longs textes de synopsis
    private String synopsis;

    private String imageUrl;
    private Double score; // La note (ex: 9.12)
    private Integer rank; // Son rang mondial (ex: 1)
}