package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.service.MemberService;
import com.example.demo.service.ex.MemberNotFoundException;
import com.example.demo.service.ex.UpdateException;
import com.example.demo.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	private Member combineMemberAccountAndMemberVO(MemberAccount memberAccount, MemberVO memberVO) {
		Member member = new  Member();
		member.setMa_id(memberAccount.getId());
		member.setId_number(memberVO.getId_number());
		member.setPhone(memberVO.getPhone());
		
		if(null != memberVO.getYear() && !"".equals(memberVO.getYear()) && 
		   null != memberVO.getMonth() && !"".equals(memberVO.getMonth()) &&
		   null != memberVO.getDate() && !"".equals(memberVO.getDate())) {
			member.setBirthday(memberVO.getYear() + "-" + memberVO.getMonth() + "-" + memberVO.getDate());
		}

		if(null != memberVO.getC_id() && !"".equals(memberVO.getC_id()) && 
		   null != memberVO.getD_id() && !"".equals(memberVO.getD_id())) {
			member.setC_id(memberVO.getC_id());
			member.setD_id(memberVO.getD_id());
		}
		member.setAddress(memberVO.getAddress());
		
		return member;
	}
	
	@Override
	public Member information(MemberAccount memberAccount) {
		// TODO Auto-generated method stub
		// 根據MemberAccount ID 取得Member 資料
		Member member = memberDao.findMemberByMa_id(memberAccount.getId());
		if(member == null) throw new MemberNotFoundException("查無會員資料");
		return member;
	}

	@Override
	public void updateInformation(MemberAccount memberAccount, MemberVO memberVO) {
		// TODO Auto-generated method stub
		// 將MemberVO 轉為Member
		Member member = combineMemberAccountAndMemberVO(memberAccount, memberVO);

		// 根據MemberAccount ID 對Member 資料進行修改
		member.setUpdate_by(memberAccount.getUsername());
		Integer result = memberDao.update(member);
		if(result <= 0) throw new UpdateException("修改會員資料時發生錯誤");
	}

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
