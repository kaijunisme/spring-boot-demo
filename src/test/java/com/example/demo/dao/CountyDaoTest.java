package com.example.demo.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.County;

@SpringBootTest
public class CountyDaoTest {

	@Autowired
	private CountyDao countyDao;

	@Test
	public void findAllCounty() {
		List<County> list = countyDao.findAllCounty();		
		if(list != null) list.forEach(county -> System.out.println(county.toString()));		
	}

}
