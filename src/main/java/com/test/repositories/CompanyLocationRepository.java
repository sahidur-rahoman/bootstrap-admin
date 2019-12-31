

package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.CompanyLocation;

public interface CompanyLocationRepository extends JpaRepository<CompanyLocation, Integer> {

}
