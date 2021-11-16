package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member extends Base {

	private String ma_id;
	private String name;
	private String id_number;
	private String birthday;
	private String phone;
	private String c_id;
	private String d_id;
	private String address;

}
