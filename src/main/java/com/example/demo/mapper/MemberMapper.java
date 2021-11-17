package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Member;

@Mapper
public interface MemberMapper {

	@Insert(" INSERT INTO test_project.member ( "
		  + "	MA_ID, NAME, ID_NUMBER, BIRTHDAY, "
		  + "	PHONE, C_ID, D_ID, ADDRESS, "
		  + "	CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
		  + " ) "
		  + " VALUE ( "
		  + "	#{ma_id}, #{name}, #{id_number}, #{birthday}, "
		  + "	#{phone}, #{c_id}, #{d_id}, #{address}, "
		  + "	#{create_by}, NOW(), #{update_by}, NOW() "
		  + " ) ")
	public Integer insert(Member member);
	
	@Select(" SELECT "
		  + "	MA_ID, NAME, ID_NUMBER, BIRTHDAY, "
		  + "	PHONE, C_ID, D_ID, ADDRESS "
		  + " FROM "
		  + "	test_project.member "
		  + " WHERE "
		  + "	MA_ID = #{ma_id} ")
	public Member findMemberByMa_id(String ma_id);
	
	@Update(" UPDATE "
		  + "	test_project.member "
		  + " SET "
		  + "	ID_NUMBER = #{id_number}, BIRTHDAY = #{birthday}, PHONE = #{phone}, "
		  + "	C_ID = #{c_id}, D_ID = #{d_id}, ADDRESS = #{address}, "
		  + "	UPDATE_BY = #{update_by}, UPDATE_TIME = NOW() "
		  + " WHERE "
		  + "	MA_ID = #{ma_id} ")
	public Integer update(Member member);
	
}
