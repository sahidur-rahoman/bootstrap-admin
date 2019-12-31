package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.Colors;

public interface ColorsRepository extends JpaRepository<Colors, Integer> {

}
