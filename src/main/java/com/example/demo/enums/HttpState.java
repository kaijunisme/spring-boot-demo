package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum HttpState {

	SUCCESS(2000, "成功"),
	NOT_FOUND(4000, "請求失敗");
	
	private int state;
	private String description;

	HttpState(int state, String description) {
		this.state = state;
		this.description = description;
	}
	
}
