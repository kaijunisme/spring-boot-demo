package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.DistrictDao;
import com.example.demo.entity.District;

@Repository
public class DistrictDaoImpl implements DistrictDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<District> findDistrictByC_id(String c_id) {
		// TODO Auto-generated method stub
		String sql = " SELECT "
				   + "		ID, C_ID, NAME "
				   + " FROM "
				   + "		test_project.district "
				   + " WHERE "
				   + "		C_ID = ? ";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<District>(District.class), new Object[] { c_id });
	}

}
