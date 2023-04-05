package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 * @Controller => 스프링이 관리하는 객체(스프링이 실행될 때 읽어들임)
 * 환경파일에 스캔할 패키지를 지정해주고 객체 위에 어노테이션 붙여줘야함
 */
@Controller
public class HomeController {
	
	public HomeController() {
		System.out.println("HomeController() call.");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
//		HomeController cont = new HomeController();
//		FrontController cont => *.do(요청이 들어오면 )
		
		return "home"; // /WEB-INF/views/home.jsp
	}
	
	//Spring 클래스가 아닌 메소드 방식으로 처리함
	//"GET" 방식 요청이 들어오면 (요청 url 패턴(정보), 실행 컨트롤 매핑해줌 RequestMapping)
	@RequestMapping(value="/info/page1", method=RequestMethod.GET)
	public String page1() {
		return "info/page1"; // /WEB-INF/views/info/page1.jsp
	}
	
}
