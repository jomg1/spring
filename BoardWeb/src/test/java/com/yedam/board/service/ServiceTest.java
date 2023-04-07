package com.yedam.board.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ServiceTest {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	//@Test
	public void get() {
		service.get(1L);
	}
	@Test
	public void getList() {
		Criteria cri = new Criteria(1,30);
		cri.setType("TCW");
		cri.setKeyword("user01");
		//cri.setPageNum(2);//메소드이용할 때
		service.getList(cri).forEach(board -> log.info(board));
	}
	
	public void modifyTest() {
		BoardVO board = new BoardVO();
		board.setTitle("수정후");
		board.setContent("수정 글본문.");
		board.setBno(1L);
		
		log.info("수정전: " + board);
		service.modify(board);
		log.info("수정전: " + board);
	}
	
	public void deleteTest() {
		service.remove(4L);
		
	}
	
	public void registerTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새글등록");
		board.setContent("글본문입니다.");
		board.setWriter("user04");

		log.info("등록전: " + board);
		service.register(board);
		log.info("등록후: " + board);
	}
}
