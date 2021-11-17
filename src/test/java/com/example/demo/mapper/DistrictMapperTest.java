package com.example.demo.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.District;

@SpringBootTest
public class DistrictMapperTest {

	@Autowired
	private DistrictMapper districtMapper;
	
	@Test
	public void findDistrictByC_id() {
		String c_id = "1";
		List<District> list = districtMapper.findDistrictByC_id(c_id);
		if(list != null) list.forEach(district -> System.out.println(district));
	}
	
}
