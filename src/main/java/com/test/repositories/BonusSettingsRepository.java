package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.BonusSettings;

public interface BonusSettingsRepository extends JpaRepository<BonusSettings, Integer>{

}