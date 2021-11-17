package com.example.demo.mapper;

import com.example.demo.entity.Member;

public interface MemberMapper {

	public Integer insert(Member member);
	public Member findMemberByMa_id(String ma_id);
	public Integer update(Member member);
	
}
