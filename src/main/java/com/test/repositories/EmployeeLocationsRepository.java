package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.EmployeeLocations;

public interface EmployeeLocationsRepository extends JpaRepository<EmployeeLocations, Integer> {

}
