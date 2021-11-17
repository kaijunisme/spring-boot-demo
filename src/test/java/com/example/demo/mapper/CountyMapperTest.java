package com.example.demo.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.County;

@SpringBootTest
public class CountyMapperTest {

	@Autowired
	private CountyMapper countyMapper;
	
	@Test
	public void findAllCounty() {
		List<County> list = countyMapper.findAllCounty();
		if(list != null) list.forEach(county -> System.out.println(county));
	}
	
}
