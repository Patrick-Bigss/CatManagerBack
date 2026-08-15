package com.example.CatManagerBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GatoRepository extends JpaRepository<com.example.CatManagerBack.entity.GatoEntity, Long> {
}