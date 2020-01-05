package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.PurchaseRequisition;

@Repository
public interface PurchaseRequisitionsRepository extends JpaRepository<PurchaseRequisition, Integer> {

}
