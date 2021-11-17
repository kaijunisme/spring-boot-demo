package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.County;

@Mapper
public interface CountyMapper {

	@Select(" SELECT "
		  + "	ID, NAME "
		  + " FROM "
		  + "	test_project.county ")
	public List<County> findAllCounty();
	
}
