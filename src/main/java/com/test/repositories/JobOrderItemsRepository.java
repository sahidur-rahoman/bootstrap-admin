package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.JobOrderItem;

@Repository
public interface JobOrderItemsRepository extends JpaRepository<JobOrderItem, Integer> {

}
