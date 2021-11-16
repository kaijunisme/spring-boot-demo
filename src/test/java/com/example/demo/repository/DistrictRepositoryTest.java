package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.District;

@SpringBootTest
public class DistrictRepositoryTest {

	@Autowired
	private DistrictRepository districtRepository;

	@Test
	public void findByCid() {
		String cid = "1";
		List<District> list = districtRepository.findByCid(cid);
		if(list != null) list.forEach(district -> System.out.println(district.toString()));	
	}

}
