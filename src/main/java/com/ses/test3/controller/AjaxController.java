package com.ses.test3.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ses.test3.domain.Info;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AjaxController {
	
	ArrayList<Info> list = new ArrayList<>();

	@GetMapping("/ajax")
	public String ajax() {
		return "ajax";
	}
	
	@ResponseBody
	@GetMapping("/ajax1")
	public String ajax1(String str) {
		return str;
	}
	
	@ResponseBody
	@PostMapping("/reply")
	public Info reply(Info info) {
		log.debug("* reply_param: name= {}, age= {}", info.getName(), info.getAge());
		list.add(info);
		return info;
	}
	
	@ResponseBody
	@GetMapping("/list")
	public ArrayList<Info> list() {
		log.debug("* list 접근_param: {}", list);
		return list;
	}
}
