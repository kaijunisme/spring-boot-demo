package com.example.demo.mapper;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.MemberAccount;

@SpringBootTest
public class MemberAccountMapperTest {

	@Autowired
	private MemberAccountMapper memberAccountMapper;
	
	@Test
	public void insert() {
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("username@email.com");
		memberAccount.setPassword("password");

		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		memberAccount.setSalt(salt);
		memberAccount.setCreate_by(memberAccount.getUsername());
		memberAccount.setUpdate_by(memberAccount.getUsername());

		Integer id = memberAccountMapper.insert(memberAccount);
		System.out.println(id);
	}
	
	@Test
	public void findMemberAccountByUsername() {
		String username = "username@email.com";
		MemberAccount memberAccount = memberAccountMapper.findMemberAccountByUsername(username);
		if(memberAccount != null) System.out.println(memberAccount.toString());		
	}
	
	@Test
	public void update() {
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setId("1");
		memberAccount.setPassword("123456");
		memberAccount.setUpdate_by("username@email.com");

		Integer result = memberAccountMapper.update(memberAccount);
		System.out.println(result);		
	}
	
}
