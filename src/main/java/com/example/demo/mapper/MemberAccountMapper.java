package com.example.demo.mapper;

import com.example.demo.entity.MemberAccount;

public interface MemberAccountMapper {

	public Integer insert(MemberAccount memberAccount);
	public MemberAccount findMemberAccountByUsername(String username);
	public Integer update(MemberAccount memberAccount);
	
}
