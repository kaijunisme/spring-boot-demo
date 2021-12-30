package com.example.demo.utils;

public class ValidFormat {

	// Description: 確認是否為身分證字號格式
	public static boolean isIdNumber(String text) {
		
		return text.matches("^[A-Z]{1}[0-9]{9}$");
	}

	// Description: 確認是否為手機號碼格式
	public static boolean isPhone(String text) {
		
		return text.matches("^09[0-9]{8}$");
	}
	
}
