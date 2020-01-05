package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.Unit;

@Repository
public interface UnitsRepository extends JpaRepository<Unit, Integer> {

}
