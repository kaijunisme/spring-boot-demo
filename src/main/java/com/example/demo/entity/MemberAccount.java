package com.example.demo.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAccount extends Base {

	private String id;

	@Email(message = "帳號必須是Email 格式")
	@NotBlank(message = "帳號不可為空")
	private String username;

	@NotBlank(message = "密碼不可為空")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w]{6,16}$", 
	 		 message = "密碼必須為長度6~16位碼大小寫英文加數字")
	private String password;
	
	private String salt;

}
