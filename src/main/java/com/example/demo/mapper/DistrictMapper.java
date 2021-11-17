package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.District;

@Mapper
public interface DistrictMapper {

	@Select(" SELECT "
		  + "	ID, NAME "
		  + " FROM "
		  + "	test_project.district "
		  + " WHERE "
		  + "	C_ID = #{c_id} ")
	public List<District> findDistrictByC_id(String c_id);
	
}
