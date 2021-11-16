package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member")
public class Member extends Base {

	@Id
	@Column(name = "MA_ID")
	private String maid;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ID_NUMBER")
	private String id_number;

	@Column(name = "BIRTHDAY")
	private String birthday;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "C_ID")
	private String cid;

	@Column(name = "D_ID")
	private String did;

	@Column(name = "ADDRESS")
	private String address;

}
