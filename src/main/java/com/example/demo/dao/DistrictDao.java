package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.District;

public interface DistrictDao {

	public List<District> findDistrictByC_id(String c_id);

}
