package com.ses.test3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ses.test3.domain.Info;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FragmentController {

	@GetMapping("fragment")
	public String fragment(Model model) {
		
		model.addAttribute("obj", new Info("홍길동", 23));
		
		return "fragment";
	}
}
