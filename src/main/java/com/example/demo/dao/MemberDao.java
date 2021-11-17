package com.example.demo.dao;

import com.example.demo.entity.Member;

public interface MemberDao {

	public Integer insert(Member member);
	public Member findMemberByMa_id(String ma_id);
	public Integer update(Member member);

}
