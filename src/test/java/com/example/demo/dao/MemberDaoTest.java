package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberDaoTest {

	@Autowired
	private MemberDao memberDao;

	@Test
	public void insert() {
		Member member = new Member();
		member.setMa_id("1");
		member.setName("測試");
		member.setId_number("A123456789");
		member.setBirthday("1990-01-01");
		member.setPhone("0987654321");
		member.setC_id("1");
		member.setD_id("1");
		member.setAddress("地址");
		member.setCreate_by("username@email.com");
		member.setUpdate_by("username@email.com");

		Integer result = memberDao.insert(member);
		System.out.println(result);
	}

	@Test
	public void findMemberByMa_id() {
		Member member = memberDao.findMemberByMa_id("1");
		System.out.println(member.toString());		
	}

	@Test
	public void update() {
		Member member = new Member();
		member.setMa_id("1");
		member.setId_number("A123456789");
		member.setBirthday("2000-01-01");
		member.setPhone("0987654321");
		member.setC_id("2");
		member.setD_id("2");
		member.setAddress("地址");
		member.setUpdate_by("username@email.com");

		Integer result = memberDao.update(member);
		System.out.println(result);		
	}

}
