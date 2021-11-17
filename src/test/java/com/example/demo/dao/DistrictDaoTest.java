package com.example.demo.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.District;

@SpringBootTest
public class DistrictDaoTest {

	@Autowired
	private DistrictDao districtDao;

	@Test
	public void findDistrictByC_id() {
		String c_id = "1";
		List<District> list = districtDao.findDistrictByC_id(c_id);
		if(list != null) list.forEach(district -> System.out.println(district.toString()));		
	}

}
