package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.StockDelivery;

@Repository
public interface StockDeliveryRepository extends JpaRepository<StockDelivery, Integer> {

}
