package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.service.MemberService;
import com.example.demo.vo.MemberVO;

@Controller
public class MemberContrller {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(
			@SessionAttribute(value = "member") MemberAccount memberAccount,
			Model model) {

		Member member = memberService.information(memberAccount);
		model.addAttribute("member", member);
		return "information";
	}
	
	@RequestMapping(value = "/information", method = RequestMethod.POST)
	public String updateInformation(
			@SessionAttribute(value = "member") MemberAccount memberAccount,
			@ModelAttribute MemberVO memberVO) {
		
		memberService.updateInformation(memberAccount, memberVO);
		return "redirect:information";
	}
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public String modifyPassword() {
		
		return "modifyPassword";
	}
	
}
