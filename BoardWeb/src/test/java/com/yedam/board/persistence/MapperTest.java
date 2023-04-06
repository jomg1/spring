package com.yedam.board.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.board.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	//@Test 어노테이션이 붙은 것들만 테스트 실행 됨. 어노테이션이 붙지 않으면 그저 메소드일 뿐임
	@Test
	public void updateTest() {
		BoardVO board = new BoardVO();
		board.setTitle("수정");
		board.setContent("글본문.");
		board.setBno(1L);
		mapper.update(board);
		
		log.info(mapper.update(board)==1? "성공" : "실패");
	}
	
	public void deleteTest() {
		log.info(mapper.delete(3L)==1? "성공" : "실패");
	}
	
	public void readTest() {
		// 3(int) -> 3 (long)
		BoardVO board = mapper.read(3L);
		log.info(board);
	}
	
	public void insertTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새글등록");
		board.setContent("글본문입니다.");
		board.setWriter("user03");
		mapper.insertSelectKey(board);
	}
	
	public void testGetTime() {
		mapper.getList().forEach(board -> log.info(board));
	}
	

	


}
