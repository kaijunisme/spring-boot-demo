package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.County;

@SpringBootTest
public class CountyRepositoryTest {

	@Autowired
	private CountyRepository countyRepository;

	@Test
	public void findAll() {
		List<County> list = countyRepository.findAll();
		if(list != null) list.forEach(county -> System.out.println(county.toString()));
	}

}
