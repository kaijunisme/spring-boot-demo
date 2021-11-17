package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.MemberDao;
import com.example.demo.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;

	@Override
	public Integer insert(Member member) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO test_project.member ( "
				   + "		MA_ID, NAME, ID_NUMBER, BIRTHDAY, PHONE, C_ID, D_ID, ADDRESS, "
				   + "		CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
				   + " ) "
				   + " VALUE ( "
				   + "		:ma_id, :name, :id_number, :birthday, :phone, :c_id, :d_id, :address, "
				   + "		:create_by, NOW(), :update_by, NOW() "
				   + " ) ";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
		return jdbcNameTemplate.update(sql, paramSource);
	}

	@Override
	public Member findMemberByMa_id(String ma_id) {
		// TODO Auto-generated method stub
		String sql = " SELECT "
				   + "		MA_ID, NAME, ID_NUMBER, BIRTHDAY, PHONE, C_ID, D_ID, ADDRESS "
				   + " FROM "
				   + "		test_project.member "
				   + " WHERE "
				   + "		MA_ID = ? ";

		List<Member> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Member>(Member.class), new Object[] { ma_id });
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Integer update(Member member) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "
				   + "		test_project.member "
				   + " SET "
				   + "		ID_NUMBER = :id_number, BIRTHDAY = :birthday, PHONE = :phone, C_ID = :c_id, D_ID = :d_id, ADDRESS = :address, "
				   + "		UPDATE_BY = :update_by, UPDATE_TIME = NOW() "
				   + " WHERE "
				   + "		MA_ID = :ma_id ";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
		return jdbcNameTemplate.update(sql, paramSource);
	}

}
