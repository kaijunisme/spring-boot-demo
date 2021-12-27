package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.vo.MemberVO;

@Controller
public class MemberContrller {

	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(@ModelAttribute MemberVO memberVO) {
		
		return "information";
	}
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public String modifyPassword() {
		
		return "modifyPassword";
	}
	
}
