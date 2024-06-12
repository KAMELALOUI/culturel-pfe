package com.kamel.sitepatrimonial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "patrimonial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class patrimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String localisation;

    @Column(length = 5000)
    private String descriptionHistorique;

    private String imageURL;

    private String uid;

    private String author = "";

    @Column(nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters if not using Lombok

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescriptionHistorique() {
        return descriptionHistorique;
    }

    public void setDescriptionHistorique(String descriptionHistorique) {
        this.descriptionHistorique = descriptionHistorique;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


