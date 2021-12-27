package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberAccountDao;
import com.example.demo.entity.MemberAccount;
import com.example.demo.service.MemberAccountService;

@Service
public class MemberAccountServiceImpl implements MemberAccountService {

	@Autowired
	private MemberAccountDao memberAccountDao;
	
	@Override
	public MemberAccount findMemberAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return memberAccountDao.findMemberAccountByUsername(username);
	}

}
