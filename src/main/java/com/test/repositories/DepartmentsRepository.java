package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.Department;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department, Integer> {

}
