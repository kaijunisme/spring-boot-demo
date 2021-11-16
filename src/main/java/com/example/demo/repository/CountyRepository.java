package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.County;

public interface CountyRepository extends JpaRepository<County, Long> {

	public List<County> findAll();

}
