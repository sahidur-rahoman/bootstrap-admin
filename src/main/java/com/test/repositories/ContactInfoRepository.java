package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.models.ContactInfo;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {

}
