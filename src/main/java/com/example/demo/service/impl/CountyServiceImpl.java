package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CountyDao;
import com.example.demo.entity.County;
import com.example.demo.service.CountyService;

@Service
public class CountyServiceImpl implements CountyService {

	@Autowired
	private CountyDao countyDao;

	@Override
	public List<County> findAllCounty() {
		// TODO Auto-generated method stub
		return countyDao.findAllCounty();
	}
	
}
