package com.ses.test3.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ses.test3.domain.Info;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FetchController {

	@GetMapping("/fetch")
	public String fetch() {
		log.debug("* fetch 접근");
		return "fetch";
	}
	
	@ResponseBody
	@GetMapping("/fetchTest")
	public String fetchTest(@RequestParam(name = "clientText") String client) {
		log.debug("* fetchTest 접근_param: {}", client);
		return client + " 받는값";
	}
	
	@ResponseBody
	@PostMapping("/fetchTest2")
	public Info fetchTest2(@RequestBody Info info) {
		log.debug("* fetchTest2 접근_param: {}", info);
		info.setName("홍길동");
		info.setAge(30);
		return info;
	}
	
	@ResponseBody
	@PostMapping("/fetchTest3")
	public ArrayList<Info> fetchTest3(@RequestBody ArrayList<Info> info) {
		for (Info i : info) {
			log.debug("* fetchTest3 접근_param: {}", i);			
		}
		return info;
	}
}
