package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAccount extends Base {

	private String id;
	private String username;
	private String password;
	private String salt;

}
