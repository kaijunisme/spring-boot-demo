package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.MemberAccount;
import com.example.demo.service.MemberAccountService;
import com.example.demo.vo.MemberAccountVO;

@Controller
public class MemberAccountController {

	@Autowired
	private MemberAccountService memberAccountService;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(
			@ModelAttribute MemberAccount memberAccount,
			@ModelAttribute(value = "MESSAGE") String message) {
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(
			@ModelAttribute MemberAccount memberAccount,
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		
		MemberAccountVO memberAccountVO = memberAccountService.login(memberAccount);
		if(memberAccountVO == null) {
			String message = memberAccountVO == null ? "帳號或密碼錯誤" : "";	
			redirectAttributes.addFlashAttribute("MESSAGE", message);
			return "redirect:login";
		}
		session.setAttribute("member", memberAccountVO);	
		return "redirect:information";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute MemberAccountVO memberAccountVO) {
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(
			@ModelAttribute MemberAccountVO memberAccountVO,
			RedirectAttributes redirectAttributes) {

		Optional<String> optional = memberAccountService.register(memberAccountVO);
		String message = optional.orElse("註冊成功");
		redirectAttributes.addFlashAttribute("MESSAGE", message);
		return "redirect:login";
	}
	
}
