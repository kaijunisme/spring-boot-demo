package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CountyDao;
import com.example.demo.entity.County;

@Repository
public class CountyDaoImpl implements CountyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<County> findAllCounty() {
		// TODO Auto-generated method stub
		String sql = " SELECT "
				   + "		ID, NAME "
				   + " FROM "
				   + "		test_project.county "
				   + " ORDER BY "
				   + "		ID ";

		List<County> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<County>(County.class));
		if(result != null && result.size() > 0) {
			return result;
		}
		return null;
	}

}
