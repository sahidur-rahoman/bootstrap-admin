package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.EmployeeInfo;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {

}
