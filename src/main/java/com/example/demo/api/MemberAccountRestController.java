package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MemberAccount;
import com.example.demo.enums.HttpState;
import com.example.demo.service.MemberAccountService;
import com.example.demo.utils.JsonResult;

@RestController
@RequestMapping(value = "/api/memberAccount")
public class MemberAccountRestController {

	@Autowired
	private MemberAccountService memberAccountService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<Boolean> checkUsernameDuplicate(@RequestParam(value = "username") String username) {
		
		MemberAccount result = memberAccountService.findMemberAccountByUsername(username);
		return new JsonResult<Boolean>(HttpState.SUCCESS.getState(), result != null ? true : false);
	}
	
}
