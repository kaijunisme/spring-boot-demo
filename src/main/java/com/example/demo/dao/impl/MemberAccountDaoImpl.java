package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.MemberAccountDao;
import com.example.demo.entity.MemberAccount;

@Repository
public class MemberAccountDaoImpl implements MemberAccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;

	@Override
	public Integer insert(MemberAccount memberAccount) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO test_project.member_account ( "
			  	   + "		USERNAME, PASSWORD, SALT, "
			  	   + "		CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
			  	   + " ) "
			  	   + " VALUE ( "
			  	   + "		:username, :password, :salt, "
			  	   + "		:create_by, NOW(), :update_by, NOW() "
			  	   + " ) ";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberAccount);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcNameTemplate.update(sql, paramSource, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public MemberAccount findMemberAccountByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = " SELECT "
				   + "		ID, USERNAME, PASSWORD, SALT "
				   + " FROM "
				   + "		test_project.member_account "
				   + " WHERE "
				   + "		USERNAME = ? ";

		List<MemberAccount> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberAccount>(MemberAccount.class), new Object[] { username });
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Integer update(MemberAccount memberAccount) {
		// TODO Auto-generated method stub
		String sql = " UPDATE "
				   + "		test_project.member_account "
				   + " SET "
				   + "		PASSWORD = :password, UPDATE_BY = :update_by, UPDATE_TIME = NOW() "
				   + " WHERE "
				   + "		ID = :id ";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberAccount);
		return jdbcNameTemplate.update(sql, paramSource);
	}

}
