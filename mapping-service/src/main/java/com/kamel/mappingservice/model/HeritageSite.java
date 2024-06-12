package com.kamel.mappingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "heritage")
@Getter

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeritageSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;


    @Column(nullable=false, length= 3000)
    private String imageURL;
    private String uid;
    private String author = "";

    @Column(name = "created_at")
    private LocalDateTime createdAt;




}