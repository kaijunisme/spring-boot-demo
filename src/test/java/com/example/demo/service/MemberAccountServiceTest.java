package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.MemberAccountDao;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.MemberAccountNotFoundException;
import com.example.demo.service.ex.MemberNotFoundException;
import com.example.demo.service.ex.PasswordNotMatchException;
import com.example.demo.service.ex.UsernameDuplicateException;
import com.example.demo.vo.MemberAccountVO;

@SpringBootTest
public class MemberAccountServiceTest {
	
	@Autowired
	private MemberAccountService memberAccountService;

	@MockBean
	private MemberService memberService;
	
	@MockBean
	private MemberAccountDao memberAccountDao;
	
	private MemberAccountVO memberAccountVO;
	
	@BeforeEach
	public void setup() {
		memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("Password1234");
		memberAccountVO.setCheckPassword("Password1234");
	}
	
	// Description: 測試註冊 - 兩次輸入密碼不相符
	@Test
	public void register_password_not_match_with_checkPassword() {
		memberAccountVO.setCheckPassword("password1234");
		
		// 進行測試
		Assertions.assertThrows(
				PasswordNotMatchException.class, 
				() -> memberAccountService.register(memberAccountVO));
	}

	// Description: 測試註冊 - 重複帳號
	@Test
	public void register_duplicate_username() {
		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("email@email.com");
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(memberAccount);

		// 進行測試
		Assertions.assertThrows(
				UsernameDuplicateException.class, 
				() -> memberAccountService.register(memberAccountVO));
	}

	// Description: 測試註冊 - 新增MemberAccount 資料時錯誤
	@Test
	public void register_MemberAccount_insert_error() {
		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);

		// 設定memberAccountDao.insert() 方法回傳假資料
		Mockito.when(memberAccountDao.insert(Mockito.any(MemberAccount.class))).thenReturn(0);

		// 進行測試
		Assertions.assertThrows(
				InsertException.class, 
				() -> memberAccountService.register(memberAccountVO));
	}

	// Description: 測試註冊 - 新增Member 資料時錯誤
	@Test
	public void register_Member_insert_error() {
		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);

		// 設定memberAccountDao.insert() 方法回傳假資料
		Mockito.when(memberAccountDao.insert(Mockito.any(MemberAccount.class))).thenReturn(1);

		// 設定memberService.insert() 方法回傳假資料
		Mockito.when(memberService.insert(Mockito.any(Member.class))).thenReturn(0);

		// 進行測試
		Assertions.assertThrows(
				InsertException.class, 
				() -> memberAccountService.register(memberAccountVO));
	}

	// Description: 測試註冊 - 成功
	@Test
	public void register_success() {
		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);

		// 設定memberAccountDao.insert() 方法回傳假資料
		Mockito.when(memberAccountDao.insert(Mockito.any(MemberAccount.class))).thenReturn(1);

		// 設定memberService.insert() 方法回傳假資料
		Mockito.when(memberService.insert(Mockito.any(Member.class))).thenReturn(1);

		// 進行測試
		Assertions.assertDoesNotThrow(() -> memberAccountService.register(memberAccountVO));
	}

	// Description: 測試登入 - 帳號不存在
	@Test
	public void login_username_not_exist() {
		// 準備測試資料
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("email@email.com");
		memberAccount.setPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);
		
		// 進行測試
		Assertions.assertThrows(
				MemberAccountNotFoundException.class, 
				() -> memberAccountService.login(memberAccount));
	}

	// Description: 測試登入 - 密碼比對錯誤
	@Test
	public void login_password_not_match() {
		// 準備測試資料
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("email@email.com");
		memberAccount.setPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		MemberAccount fake = new MemberAccount();
		fake.setPassword("fake password");
		fake.setSalt("fake salt");
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(fake);
		
		// 進行測試
		Assertions.assertThrows(
				PasswordNotMatchException.class, 
				() -> memberAccountService.login(memberAccount));
	}

	// Description: 測試登入 - 查無對應Member 資料
	@Test
	public void login_member_not_found() {
		// 準備測試資料
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("email@email.com");
		memberAccount.setPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		MemberAccount fake = new MemberAccount();
		fake.setId("1");
		fake.setPassword("D02478AB713ABA5E8E431D59640972DE");
		fake.setSalt("1BA4CAF360664CC880175B3153A6031D");
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(fake);

		// 設定memberService.findMemberByMa_id() 方法回傳假資料
		Mockito.when(memberService.findMemberByMa_id("1")).thenReturn(null);

		// 進行測試
		Assertions.assertThrows(
				MemberNotFoundException.class, 
				() -> memberAccountService.login(memberAccount));
	}
	
}
