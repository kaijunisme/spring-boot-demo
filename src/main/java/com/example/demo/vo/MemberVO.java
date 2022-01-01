package com.example.demo.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {

	private String name;
	
	private String username;

	@Pattern(regexp = "^[A-Z]{1}[0-9]{9}$", 
	 		 message = "身分證字號格式錯誤")
	private String id_number;
	
	@Min(value = 1951, 
		 message = "年份錯誤")
	private String year;
	
	@Size(min = 1, 
		  max = 12, 
		  message = "月份錯誤")
	private String month;

	@Size(min = 1, 
		  max = 31, 
		  message = "日期錯誤")
	private String date;

	@Pattern(regexp = "^09[0-9]{8}$", 
	 		 message = "聯絡電話格式錯誤")
	private String phone;
	
	private String c_id;
	
	private String d_id;
	
	private String address;
	
}
