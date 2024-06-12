package com.kamel.sitepatrimonial.repository;

import com.kamel.sitepatrimonial.models.patrimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrimonialRepository extends JpaRepository<patrimonial, Long> {

    List<patrimonial> findByNomContainingIgnoreCase(String nom);

    List<patrimonial> findByLocalisationContainingIgnoreCase(String localisation);

    @Query("SELECT p FROM patrimonial p WHERE p.descriptionHistorique LIKE %:keyword%")
    List<patrimonial> searchByDescription(String keyword);
}