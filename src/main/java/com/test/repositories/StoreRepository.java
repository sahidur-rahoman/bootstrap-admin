package com.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.models.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

	@Query("SELECT str.name FROM stores str WHERE str.id = :id AND str.is_deleted = 0")
	public String getStoreName(@Param("id") Integer id);

	@Query("SELECT str.name FROM stores str WHERE str.is_deleted = 0")
	public List<String> getAllStoreName();

}
