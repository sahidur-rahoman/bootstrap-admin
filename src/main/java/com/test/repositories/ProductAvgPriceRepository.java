package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.ProductAvgPrice;

@Repository
public interface ProductAvgPriceRepository extends JpaRepository<ProductAvgPrice, Integer> {

}
