package com.example.demo.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult<T> {

	private Integer state;
	private String message;
	private T data;

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}

	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	
}
