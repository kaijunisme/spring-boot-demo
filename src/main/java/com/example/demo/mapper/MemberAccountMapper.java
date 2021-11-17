package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.MemberAccount;

@Mapper
public interface MemberAccountMapper {

	@Insert(" INSERT INTO test_project.member_account ( "
		  + "	USERNAME, PASSWORD, SALT, "
		  + "	CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
		  + " ) "
		  + " VALUE ( "
		  + "	#{username}, #{password}, #{salt}, "
		  + "	#{create_by}, NOW(), #{update_by}, NOW() "
		  + " ) ")
	public Integer insert(MemberAccount memberAccount);
	
	@Select(" SELECT "
		  + "	ID, USERNAME, PASSWORD, SALT "
		  + " FROM "
		  + "	test_project.member_account "
		  + " WHERE "
		  + "	USERNAME = #{username} ")
	public MemberAccount findMemberAccountByUsername(String username);
	
	@Update(" UPDATE "
		  + "	test_project.member_account "
		  + " SET "
		  + "	PASSWORD = #{password}, UPDATE_BY = #{update_by}, UPDATE_TIME = NOW() "
		  + " WHERE "
		  + "	ID = #{id} ")
	public Integer update(MemberAccount memberAccount);
	
}
