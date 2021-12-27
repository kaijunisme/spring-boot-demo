package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.MemberAccount;
import com.example.demo.vo.MemberAccountVO;

@Controller
public class MemberAccountController {

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(@ModelAttribute MemberAccount memberAccount) {
		
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute MemberAccountVO memberAccountVO) {
		
		return "register";
	}
	
}
