package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.models.PurchaseRequisitionItem;

@Repository
public interface PurchaseRequisitionItemsRepository extends JpaRepository<PurchaseRequisitionItem, Integer> {

}
