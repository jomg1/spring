package com.yedam.board.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.board.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value="/getText", produces = "text/plain;charset=UTF-8")//prudouce attribute에 어떤 형식의 데이터를 넘겨줄 지, 인코딩 방식 지정 가능
	public String getText() {
		return "안녕하세요";
	}
	
	@GetMapping(value="/getSample", 
			produces = {//MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		SampleVO vo = new SampleVO(100, "길동", "홍");
		return vo;
	}
	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		SampleVO vo = new SampleVO(100, "길동", "홍");
		return vo;
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			list.add(new SampleVO(i+10, "firstName"+i, "lastName"+i));
		}
		return list;
	}
	
	@GetMapping("/product/{cat}/{pid}") //url 경로상의 변수 지정. 기존:product?cat=bags&pid=1001 //attribute 여러개 주고싶으면 value, 생략하면 바로 value
	public String[] getPath(@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) { //int등 기본 타입 X 객체타입으로만 값을 넘겨야하므로 Integer
		return new String[] { "Category: " + cat, "ProductId: " + pid };
		
	}
	
	// 컨트롤에 key=value 형식으로 값을 전달
	// json 형식의 값을 전달(@RequestBody)
	// post 방식 테스트 하는 방법 : 크롬 웹스토어 -> rest -> YARC -> url 입력, payload {"mno":1001,"firstName":"kildong","lastName":"Hong"} 입력
	@PostMapping("/sample")
	public SampleVO convert(@RequestBody SampleVO sample) {//@RequestBody : json 포맷의 데이터를 받아와 처리 해 줄 수 있음
		return sample;
	}

}
