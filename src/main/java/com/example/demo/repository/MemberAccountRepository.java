package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MemberAccount;

public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long> {

	public MemberAccount findByUsername(String username);

}
