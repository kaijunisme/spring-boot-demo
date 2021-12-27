package com.example.demo.service;

import com.example.demo.entity.MemberAccount;

public interface MemberAccountService {

	// 資料庫操作
	public MemberAccount findMemberAccountByUsername(String username);	

}
