package com.example.demo.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.example.demo.dao.MemberAccountDao;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAccount;
import com.example.demo.service.MemberAccountService;
import com.example.demo.service.MemberService;
import com.example.demo.utils.ValidFormat;
import com.example.demo.vo.MemberAccountVO;

@Service
public class MemberAccountServiceImpl implements MemberAccountService {

	@Autowired
	private MemberAccountDao memberAccountDao;

	@Autowired
	private MemberService memberService;
	
	private String getMd5Password(String password, String salt) {
		// 對password + salt 進行三次加密
		String str = password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	
	@Override
	public MemberAccountVO login(MemberAccount memberAccount) {
		// TODO Auto-generated method stub
		// 檢查帳號是否存在
		MemberAccount data = memberAccountDao.findMemberAccountByUsername(memberAccount.getUsername());
		if(data == null) return null;

		// 使用資料庫鹽值對輸入密碼進行加密
		String md5Password = getMd5Password(memberAccount.getPassword(), data.getSalt());

		// 比對密碼是否相等
		if(!md5Password.equals(data.getPassword())) return null;
		
		// 取得對應Member 資料
		Member member = memberService.findMemberByMa_id(data.getId());
		if(member == null) return null;
		
		// 組合資料為MemberAccountVO
		MemberAccountVO memberAccountVO = new MemberAccountVO();
		memberAccountVO.setUsername(memberAccount.getUsername());
		memberAccountVO.setName(member.getName());
		return memberAccountVO;
	}

	@Override
	public Optional<String> register(MemberAccountVO memberAccountVO) {
		// TODO Auto-generated method stub
		// 驗證欄位是否填寫及格式
		if(!ValidFormat.isEmail(memberAccountVO.getUsername())) return Optional.of("帳號必須是Email 格式");
		if(!ValidFormat.isPassword(memberAccountVO.getPassword())) return Optional.of("密碼必須為長度6~16位碼大小寫英文加數字");
		if(!memberAccountVO.getPassword().equals(memberAccountVO.getCheckPassword())) return Optional.of("兩次輸入密碼不相符");
		
		// 檢查帳號是否重複註冊
		MemberAccount data = memberAccountDao.findMemberAccountByUsername(memberAccountVO.getUsername());
		if(data != null) return Optional.of("該帳號已被使用");
		
		// 產生鹽值
		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		
		// 密碼加密
		String md5Password = getMd5Password(memberAccountVO.getPassword(), salt);

		// 新增MemberAccount 資料
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setUsername(memberAccountVO.getUsername());
		memberAccount.setPassword(md5Password);
		memberAccount.setSalt(salt);
		memberAccount.setCreate_by(memberAccountVO.getUsername());
		memberAccount.setUpdate_by(memberAccountVO.getUsername());
		Integer id = memberAccountDao.insert(memberAccount);
		if(id == 0) return Optional.of("新增會員帳號時發生錯誤");

		// 新增Member 資料
		Member member = new Member();
		member.setMa_id(String.valueOf(id));
		member.setName(memberAccountVO.getName());
		member.setCreate_by(memberAccountVO.getUsername());
		member.setUpdate_by(memberAccountVO.getUsername());
		Integer result = memberService.insert(member);
		if(result == 0) return Optional.of("新增會員資料時發生錯誤");
		
		return Optional.empty();
	}

	@Override
	public MemberAccount findMemberAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return memberAccountDao.findMemberAccountByUsername(username);
	}

}
