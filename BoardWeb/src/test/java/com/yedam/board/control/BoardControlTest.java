package com.yedam.board.control;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.yedam.board.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration // 컨트롤 테스트 할 경우에 붙임
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControlTest {

	// 처리된 결과 페이지, url 패턴 처리 : ApplicationContext (컨트롤의 test를 위해서는
	// ApplicationContext 필요함.), spring이 관리함
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;

	private MockMvc mockMvc; // url -> control -> .jsp (MockMvc툴 활용)

	// mockMvc에 인스턴스 만들기(호출하기)
	@Before // 테스트클래스 실행될 때 마다 먼저 호출. mockMvc에 값을 먼저 넣어주기 위해서.
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); // 인스턴스 호출
	}

	@Test
	public void getTest() {
		RequestBuilder rb = MockMvcRequestBuilders.get("/board/get")
				.param("bno", "1");
		try {
			String vn = mockMvc.perform(rb) // RequestBuilder값이 perform의 매개값으로 들어감
					.andReturn().
					getModelAndView().
					getViewName();

			log.info(vn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "5");
		try {
			String vn = mockMvc.perform(rb) // RequestBuilder값이 perform의 매개값으로 들어감
					.andReturn().
					getModelAndView().
					getViewName();

			log.info(vn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void modifyTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/modify")
				.param("title", "수정된 글입니다") // 파라미터 이름과 값을 넣어줌
				.param("content", "글 내용입니다").param("wirter", "user01").param("bno", "1");
		try {
			String vn = mockMvc.perform(rb) // RequestBuilder값이 perform의 매개값으로 들어감
					.andReturn().
					getModelAndView().
					getViewName();

			log.info(vn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerTest() {
		RequestBuilder rb = MockMvcRequestBuilders.post("/board/register")// post 타입의 경우 .param으로
				.param("title", "새로운 글입니다") // 파라미터 이름과 값을 넣어줌
				.param("content", "여긴 글 내용입니다").param("writer", "user07");
		try {
			String vn = mockMvc.perform(rb) // RequestBuilder값이 perform의 매개값으로 들어감
					.andReturn().
					getModelAndView().
					getViewName();

			log.info(vn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listTest() { // 목록 리스트 출력
		// /board/list
		try {
			ModelMap map = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))// url 처리해주는 클래스 :
																						// MockMvcRequestBuilders.get(post)
					.andReturn() // 처리 결과값을 받아오는 메소드
					.getModelAndView() // model에 저장된 정보 처리
					.getModelMap(); // 담겨진 값을 Map타입으로 가지고 오는 메소드 즉 최종 결과값의 타입이 된다

			List<BoardVO> list = (List<BoardVO>) map.get("list");
			for (BoardVO vo : list)
				log.info(vo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
