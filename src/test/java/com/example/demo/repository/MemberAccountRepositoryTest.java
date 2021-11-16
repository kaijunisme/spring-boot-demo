package com.example.demo.repository;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.MemberAccount;

@SpringBootTest
public class MemberAccountRepositoryTest {

	@Autowired
	private MemberAccountRepository memberAccountRepository;

	@Test
	public void insert() {
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername("username@email.com");
		memberAccount.setPassword("password");

		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		memberAccount.setSalt(salt);
		memberAccount.setCreate_by(memberAccount.getUsername());
		memberAccount.setUpdate_by(memberAccount.getUsername());

		memberAccountRepository.save(memberAccount);
		System.out.println(memberAccount.getId());
	}

	@Test
	public void findByUsername() {
		String username = "username@email.com";
		MemberAccount memberAccount = memberAccountRepository.findByUsername(username);
		if(memberAccount != null) System.out.println(memberAccount.toString());
	}

	@Test
	public void update() {
		String username = "username@email.com";
		MemberAccount memberAccount = memberAccountRepository.findByUsername(username);
		memberAccount.setPassword("123456");

		memberAccountRepository.save(memberAccount);
	}

}
