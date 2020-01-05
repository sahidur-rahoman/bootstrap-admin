package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.models.CompanyLocation;

@Repository
public interface CompanyLocationRepository extends JpaRepository<CompanyLocation, Integer> {
	
	@Query("SELECT clr.name from colors clr WHERE clr.id = :id")
	public Integer getCompanyLocationIdByCompanyId(@Param("id") Integer id);
}
