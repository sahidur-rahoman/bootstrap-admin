package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.JobOrderItems;

public interface JobOrderItemsRepository extends JpaRepository<JobOrderItems, Integer> {

}
