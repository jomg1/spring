package com.yedam.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

//@RequestMapping
@Controller
@Log4j
public class BookController {

	@GetMapping("/index")
	public void index() {
		//첫 화면
	}
	
	@GetMapping("/register")
	public void register() {
		// 등록 화면
	}
	
	
	
	
	
}
