package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.MemberDao;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.service.ex.MemberNotFoundException;
import com.example.demo.service.ex.UpdateException;
import com.example.demo.vo.MemberVO;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@MockBean
	private MemberDao memberDao;
	
	@MockBean
	private MemberAccountService memberAccountService;

	private MemberAccount memberAccount;
	
	private MemberVO memberVO;
	
	final String id = "1", username = "email@email.com";
	
	@BeforeEach
	public void setup() {
		memberAccount = new MemberAccount();
		memberAccount.setId(id);
		memberAccount.setUsername(username);
		
		memberVO = new MemberVO();
		memberVO.setUsername(username);
		memberVO.setId_number("B223456789");
	}
	
	// Description: 測試會員資料 - 查無Member 資料
	@Test
	public void information_member_not_found() {
		// 設定memberDao.findMemberByMa_id() 方法回傳假資料
		Mockito.when(memberAccountService.findMemberAccountByUsername(Mockito.anyString())).thenReturn(memberAccount);

		// 設定memberDao.findMemberByMa_id() 方法回傳假資料
		Mockito.when(memberDao.findMemberByMa_id(Mockito.anyString())).thenReturn(null);

		// 進行測試
		Assertions.assertThrows(
				MemberNotFoundException.class, 
				() -> memberService.information(memberAccount));
	}

	// Description: 測試修改會員資料 - 修改Member 資料時錯誤
	@Test
	public void updateInformation_update_error() {
		// 設定memberDao.update() 方法回傳假資料
		Mockito.when(memberDao.update(Mockito.any(Member.class))).thenReturn(0);

		// 進行測試
		Assertions.assertThrows(
				UpdateException.class, 
				() -> memberService.updateInformation(memberAccount, memberVO));		
	}
	
}
