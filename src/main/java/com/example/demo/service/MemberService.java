package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.vo.MemberVO;

public interface MemberService {

	// 業務邏輯
	public Member information(MemberAccount memberAccount);
	public void updateInformation(MemberAccount memberAccount, MemberVO memberVO);
	
	// 資料庫操作
	public Integer insert(Member member);
	public Member findMemberByMa_id(String ma_id);
	
}
