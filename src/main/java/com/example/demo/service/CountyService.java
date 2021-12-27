package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.County;

public interface CountyService {

	// 資料庫操作
	public List<County> findAllCounty();
	
}
