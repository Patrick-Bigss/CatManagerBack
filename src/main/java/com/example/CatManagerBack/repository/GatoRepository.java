package com.example.CatManagerBack.repository;

import com.example.CatManagerBack.entity.GatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GatoRepository
        extends JpaRepository<GatoEntity, Long> {

    Optional<GatoEntity> findByCatApiId(String catApiId);

}