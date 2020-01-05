package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.SampleRequisition;

@Repository
public interface SampleRequisitionsRepository extends JpaRepository<SampleRequisition, Integer> {

}
