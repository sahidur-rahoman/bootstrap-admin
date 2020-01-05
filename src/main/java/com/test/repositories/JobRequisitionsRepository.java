package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.JobRequisition;

@Repository
public interface JobRequisitionsRepository extends JpaRepository<JobRequisition, Integer> {

}
