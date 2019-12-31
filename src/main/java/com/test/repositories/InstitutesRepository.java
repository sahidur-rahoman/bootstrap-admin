package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.Institutes;

public interface InstitutesRepository extends JpaRepository<Institutes, Integer> {

}
