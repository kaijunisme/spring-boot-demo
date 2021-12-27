package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.District;

public interface DistrictService {

	// 資料庫操作
	public List<District> findDistrictByC_id(String c_id);
	
}
