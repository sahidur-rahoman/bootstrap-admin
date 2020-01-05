package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.Color;

@Repository
public interface PromotionSettingsRepository extends JpaRepository<Color, Integer> {

}
