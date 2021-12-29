package com.example.demo.service;

import com.example.demo.entity.Member;

public interface MemberService {

	// 資料庫操作
	public Integer insert(Member member);
	public Member findMemberByMa_id(String ma_id);
	
}
