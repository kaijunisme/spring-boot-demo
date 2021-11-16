package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

	public List<District> findByCid(String cid);

}
