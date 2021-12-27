package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DistrictDao;
import com.example.demo.entity.District;
import com.example.demo.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDao districtDao;

	@Override
	public List<District> findDistrictByC_id(String c_id) {
		// TODO Auto-generated method stub
		return districtDao.findDistrictByC_id(c_id);
	}
	
}
