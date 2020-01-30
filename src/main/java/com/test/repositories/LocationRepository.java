package com.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	@Query("SELECT loc.name FROM locations loc WHERE loc.id = :id AND loc.is_deleted = 0")
	public String getLocationName(@Param("id") Integer id);

	@Query("SELECT loc.name FROM locations loc WHERE loc.is_deleted = 0")
	public List<String> getLocationNames();

	@Query("SELECT loc.name FROM locations loc WHERE loc.store_id = :strid AND loc.is_deleted = 0")
	public List<String> getLocationNamesByStoreId(@Param("strid") Integer storeId);

	@Query("SELECT loc FROM locations loc WHERE loc.store_id = :strid AND loc.is_deleted = 0")
	public List<Location> getAllLocationByStoreId(@Param("strid") Integer storeId);

}
