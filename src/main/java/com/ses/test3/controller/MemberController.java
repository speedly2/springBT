package com.ses.test3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ses.test3.domain.Member;
import com.ses.test3.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/join")
	public String join() {
		log.debug("* MemberController / join(GET)");
		return "join";
	}
	
	@PostMapping("/join")
	public String join2(Member member) {
		log.debug("* MemberController / join(POST)");
		log.debug("* param: {}", member);
		
		service.insert(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		log.debug("* MemberController / login(GET)");
		return "loginForm";
	}
}
