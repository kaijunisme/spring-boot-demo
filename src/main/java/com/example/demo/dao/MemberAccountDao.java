package com.example.demo.dao;

import com.example.demo.entity.MemberAccount;

public interface MemberAccountDao {

	public Integer insert(MemberAccount memberAccount);
	public MemberAccount findMemberAccountByUsername(String username);
	public Integer update(MemberAccount memberAccount);

}
