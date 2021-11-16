package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void insert() {
		Member member = new Member();
		member.setMaid("1");
		member.setName("使用者");
		member.setBirthday("1990-01-01");
		member.setPhone("0987654321");
		member.setCreate_by("username@email.com");
		member.setUpdate_by("username@email.com");

		memberRepository.save(member);
	}

	@Test
	public void findByMaid() {
		String maid = "1";
		Member member = memberRepository.findByMaid(maid);
		if(member != null) System.out.println(member.toString());
	}

	@Test
	public void update() {
		String maid = "1";
		Member member = memberRepository.findByMaid(maid);
		member.setId_number("A123456789");
		member.setUpdate_by("username@email.com");

		memberRepository.save(member);		
	}

}
