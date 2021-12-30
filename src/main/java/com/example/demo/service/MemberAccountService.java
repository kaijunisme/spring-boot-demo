package com.example.demo.service;

import com.example.demo.entity.MemberAccount;
import com.example.demo.vo.MemberAccountVO;

public interface MemberAccountService {

	// 業務邏輯
	public MemberAccountVO login(MemberAccount memberAccount);
	public void register(MemberAccountVO memberAccountVO);
	
	// 資料庫操作
	public MemberAccount findMemberAccountByUsername(String username);	

}
