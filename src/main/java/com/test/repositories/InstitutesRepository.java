package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.Institute;

@Repository
public interface InstitutesRepository extends JpaRepository<Institute, Integer> {

}
