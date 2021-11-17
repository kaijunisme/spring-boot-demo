package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.District;

public interface DistrictMapper {

	public List<District> findDistrictByC_id(String c_id);
	
}
