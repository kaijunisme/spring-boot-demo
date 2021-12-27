package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAccountVO {

	private String name;
	private String username;
	private String password;
	private String checkPassword;
	
}
