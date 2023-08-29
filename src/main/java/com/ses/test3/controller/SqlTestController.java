package com.ses.test3.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ses.test3.domain.Info;
import com.ses.test3.domain.SeqMember;
import com.ses.test3.service.SqlService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SqlTestController {
	
	@Autowired
	SqlService service;

	@GetMapping("sqlTest")
	public String sqlTest() {
		return "sqlTest";
	}
	
	@ResponseBody
	@PostMapping("sqlTest")
	public ArrayList<Info> sqlTest2(@RequestBody ArrayList<Info> list) {
		
		log.debug("sqlTest_param : {}", list);
		int n = service.insertInfo(list);
		log.debug("insert_result : {}", n);
		
		ArrayList<Info> result = service.selectInfo();
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("sqlTest2")
	public ArrayList<SeqMember> sqlTest22() {
		
		ArrayList<SeqMember> list = new ArrayList<>();
		list.add(new SeqMember(0, "홍1", 1));
		list.add(new SeqMember(0, "홍2", 2));
		list.add(new SeqMember(0, "홍3", 3));
		int n = service.insertSeqMember(list);
		log.debug("insert_result : {}", n);
		
		ArrayList<SeqMember> result = service.selectSeqMember();
		
		return result;
	}
	
	@ResponseBody
	@GetMapping("sqltestDelete")
	public ArrayList<Info> sqltestDelete() {
		
		int n = service.deleteInfo();
		log.debug("delete_result : {}", n);
		
		ArrayList<Info> result = service.selectInfo();
		
		return result;
	}
	
}
