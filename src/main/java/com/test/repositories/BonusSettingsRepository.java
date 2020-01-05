package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.BonusSetting;

@Repository
public interface BonusSettingsRepository extends JpaRepository<BonusSetting, Integer>{

}