package com.statutory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statutory.model.StatutoryModel;

public interface StatutoryRepository extends JpaRepository<StatutoryModel,Long> {
	
	Optional<StatutoryModel>findByempid(Long empid);

}
