package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.JobOrders;

public interface JobOrdersRepository extends JpaRepository<JobOrders, Integer> {

}
