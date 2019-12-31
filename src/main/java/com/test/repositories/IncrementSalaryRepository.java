package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.IncrementSalary;

public interface IncrementSalaryRepository extends JpaRepository<IncrementSalary, Integer> {

}
