package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public Integer insert(Member member) {
		// TODO Auto-generated method stub
		return memberDao.insert(member);
	}

	@Override
	public Member findMemberByMa_id(String ma_id) {
		// TODO Auto-generated method stub
		return memberDao.findMemberByMa_id(ma_id);
	}
	
}
