package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	public SampleController() {
		log.info("sampleController() call.");
	}
	
	// jsp : command 구현 (인터페이스) 클래스
	// spring : 메소드에 command, url패턴 등을 모두 구현, 관리 용이
	@RequestMapping("page1")
	public String basic() {
		// (url패턴) /sample/page1 호출 => 해당 메소드 실행
		log.info("basic...");
		return "page1"; // /WEB-INF/views/page1.jsp
	}
	
	@RequestMapping("page2")
	public void baisc2() {
		log.info("basic2...");
		// 반환값이 없음 : sample/page2 => /WEB-INF/views/sample/page.jsp (prefix, 서스픽스 사이에 값을 넣어줌)
	}
	
//	@RequestMapping("myInfo")
//	public void myInfo() {
//		log.info("myInfo.");
//	}
	
	// 위, 아래 둘 다 가능
	@RequestMapping("myInfo")
	public String basic3() {
		log.info("myInfo");
		return "sample/myInfo"; //page1은 위치가 같은 위치, myInfo는 위치가 다르기에 지정
	}
	
	//@RequestMapping 요청 방식 따로 지정 가능
	@RequestMapping(value="page3", method = { RequestMethod.GET, RequestMethod.POST })
	public String basic4() {
		return "page3";
	}
	
	@GetMapping("page4")
	public String basic5() { // RequestMethod.GET(으로만 한정)
		return "page4";
	}
	
	@PostMapping("page5")
	public String basic6() { // RequestMethod.POST
		return "page5";
	}
	
	@GetMapping("ex01")
	public String ex01(@RequestParam("name") String name) {
		log.info("name=>" + name);
		return "ex01";
	}
	
	@GetMapping("ex02")
	public String ex02(SampleDTO dto, Model model, int score) {
		log.info(dto);
		model.addAttribute("name", dto.getName()); // request.setAttribute("name", dto.getName());의 역할을 하는 것이 model
		model.addAttribute("score", score); // model에는 참조값을 담아서 반환.->이라고 하셨는데("age, new Integer(10));의 방식으로 변환되는 듯..
	
		return "ex02";
	}
	
	@GetMapping("ex03")
	public String ex03(@RequestParam("ids") ArrayList<String> ids) {
		
		for(String id : ids) {
			log.info(id);
		}
		return "ex02";
	}
	
	@GetMapping("ex04")
	//SampleDTO(자바 객체)를 json포맷으로 넘겨주겠다는 뜻
	//요청 url, 매개변수, 반환되는 값
	//ex04 요청하면 반환되는 타입이 smapleDTO, 이걸 실행하면 json 타입으로 생성해주는 것이 @ResponseBody
	public @ResponseBody SampleDTO ex04() {
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("Hong"); // 내부적으로 json 생성
		
		return dto;
	}
}
