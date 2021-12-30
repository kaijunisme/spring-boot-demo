package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindException;

import com.example.demo.entity.MemberAccount;
import com.example.demo.service.MemberAccountService;
import com.example.demo.service.ex.MemberAccountNotFoundException;
import com.example.demo.vo.MemberAccountVO;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberAccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MemberAccountService memberAccountService;
	
	private MemberAccount memberAccount;
	
	private MockHttpServletRequestBuilder request(String url, String name, Object value) throws JsonProcessingException {
        return MockMvcRequestBuilders.post(url)
        							 .contentType(MediaType.APPLICATION_JSON)
									 .flashAttr(name, value);
	}

	@BeforeEach
	public void setup() {
		memberAccount = new MemberAccount();
		memberAccount.setUsername("email@email.com");
		memberAccount.setPassword("Password1234");
	}	

	// Description: 測試註冊 - 傳入格式不符
	@Test
	public void register_format_error() throws Exception {
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setName("");
		memberAccountVO.setUsername("");
		memberAccountVO.setPassword("");
		memberAccountVO.setCheckPassword("");
		mockMvc.perform(request("/register", "memberAccountVO", memberAccountVO))
	           .andExpect(status().is3xxRedirection())
	           .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException));
	}

	// Description: 測試登入 - 傳入格式不符
	@Test
	public void login_format_error() throws Exception {
		memberAccount.setUsername("");
		memberAccount.setPassword("");
		mockMvc.perform(request("/login", "memberAccount", memberAccount))
	           .andExpect(status().is3xxRedirection())
	           .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException));
	}

	// Description: 測試登入 - 帳號或密碼錯誤
	@Test
	public void login_username_password_error() throws Exception {
		Mockito.doThrow(MemberAccountNotFoundException.class).when(memberAccountService).login(Mockito.any(MemberAccount.class));
		mockMvc.perform(request("/login", "memberAccount", memberAccount))
	           .andExpect(status().is3xxRedirection())
	           .andExpect(result -> assertTrue(result.getResolvedException() instanceof MemberAccountNotFoundException));
	}
	
}
