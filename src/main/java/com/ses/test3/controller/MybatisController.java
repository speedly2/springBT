package com.ses.test3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ses.test3.dao.SeqDAO;
import com.ses.test3.domain.SeqMember;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MybatisController {
	
	@Autowired
	SeqDAO dao;
	
	@GetMapping("selectKey")
	public String selectKey() {
		return "selectKey";
	}
	
	@PostMapping("selectKey")
	public String selectKeyInsert(SeqMember sm) {
		
		log.debug("SeqMember: {}", sm);
		dao.insertSelectKey(sm);
		
		return "home";
	}
}
