package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.MemberAccountDao;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.vo.MemberAccountVO;

@SpringBootTest
public class MemberAccountServiceTest {

	@Autowired
	private MemberAccountService memberAccountService;

	@MockBean
	private MemberService memberService;
	
	@MockBean
	private MemberAccountDao memberAccountDao;
	
	// Description: 測試註冊 - 帳號格式錯誤
	@Test
	public void register_username_format_error() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("not email");
		
		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		assertEquals("帳號必須是Email 格式", result.orElse(""));
	}

	// Description: 測試註冊 - 密碼格式錯誤
	@Test
	public void register_password_format_error() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("password");
		
		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		Assertions.assertEquals("密碼必須為長度6~16位碼大小寫英文加數字", result.orElse(""));
	}

	// Description: 測試註冊 - 兩次輸入密碼不相符
	@Test
	public void register_password_not_match_with_checkPassword() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("Password1234");
		memberAccountVO.setCheckPassword("password1234");
		
		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		Assertions.assertEquals("兩次輸入密碼不相符", result.orElse(""));
	}

	// Description: 測試註冊 - 重複帳號
	@Test
	public void register_duplicate_username() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("Password1234");
		memberAccountVO.setCheckPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("email@email.com");
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(memberAccount);

		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		Assertions.assertEquals("該帳號已被使用", result.orElse(""));		
	}

	// Description: 測試註冊 - 新增MemberAccount 資料時錯誤
	@Test
	public void register_MemberAccount_insert_error() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("Password1234");
		memberAccountVO.setCheckPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);

		// 設定memberAccountDao.insert() 方法回傳假資料
		Mockito.when(memberAccountDao.insert(Mockito.any(MemberAccount.class))).thenReturn(0);

		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		Assertions.assertEquals("新增會員帳號時發生錯誤", result.orElse(""));		
	}

	// Description: 測試註冊 - 新增Member 資料時錯誤
	@Test
	public void register_Member_insert_error() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("Password1234");
		memberAccountVO.setCheckPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);

		// 設定memberAccountDao.insert() 方法回傳假資料
		Mockito.when(memberAccountDao.insert(Mockito.any(MemberAccount.class))).thenReturn(1);

		// 設定memberService.insert() 方法回傳假資料
		Mockito.when(memberService.insert(Mockito.any(Member.class))).thenReturn(0);

		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		Assertions.assertEquals("新增會員資料時發生錯誤", result.orElse(""));
	}

	// Description: 測試註冊 - 成功
	@Test
	public void register_success() {
		// 準備測試資料
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername("email@email.com");
		memberAccountVO.setPassword("Password1234");
		memberAccountVO.setCheckPassword("Password1234");

		// 設定memberAccountDao.findMemberAccountByUsername() 方法回傳假資料
		Mockito.when(memberAccountDao.findMemberAccountByUsername("email@email.com")).thenReturn(null);

		// 設定memberAccountDao.insert() 方法回傳假資料
		Mockito.when(memberAccountDao.insert(Mockito.any(MemberAccount.class))).thenReturn(1);

		// 設定memberService.insert() 方法回傳假資料
		Mockito.when(memberService.insert(Mockito.any(Member.class))).thenReturn(1);

		// 進行測試
		Optional<String> result = memberAccountService.register(memberAccountVO);
		Assertions.assertEquals(null, result.orElse(null));
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
		MemberAccountVO result = memberAccountService.login(memberAccount);
		Assertions.assertEquals(null, result);
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
		MemberAccountVO result = memberAccountService.login(memberAccount);
		Assertions.assertEquals(null, result);		
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
		MemberAccountVO result = memberAccountService.login(memberAccount);
		Assertions.assertEquals(null, result);		
	}
	
}
