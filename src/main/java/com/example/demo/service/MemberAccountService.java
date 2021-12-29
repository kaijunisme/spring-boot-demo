package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.MemberAccount;
import com.example.demo.vo.MemberAccountVO;

public interface MemberAccountService {

	// 業務邏輯
	public MemberAccountVO login(MemberAccount memberAccount);
	public Optional<String> register(MemberAccountVO memberAccountVO);
	
	// 資料庫操作
	public MemberAccount findMemberAccountByUsername(String username);	

}
