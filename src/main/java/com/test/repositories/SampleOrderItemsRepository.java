package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.SampleOrderItem;

@Repository
public interface SampleOrderItemsRepository extends JpaRepository<SampleOrderItem, Integer> {

}
