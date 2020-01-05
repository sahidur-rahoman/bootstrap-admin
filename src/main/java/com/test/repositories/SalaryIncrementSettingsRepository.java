package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.SalaryIncrementSetting;

@Repository
public interface SalaryIncrementSettingsRepository extends JpaRepository<SalaryIncrementSetting, Integer> {

}
