package com.yedam.board.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyPageDTO;
import com.yedam.board.domain.ReplyVO;
import com.yedam.board.service.ReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
public class ReplyController {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyService replyService;
	
	//등록
	@PostMapping(value="/new", produces="text/plain;charset=UTF-8")
	private ResponseEntity create(@RequestBody ReplyVO vo) {
		// String type 문자열 : 성공 / 실패, ResponseEntity 타입 : 성공/code, 실패/잘못된 요청코드를 같이 담아 넘길 수 있음
		int result = replyService.register(vo);
		if(result == 1) {
			return new ResponseEntity<>("성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//글번호->댓글정보 보여줌
	@GetMapping(value="/pages/{bno}/{page}") // pages/300 => pages?bno=300
	//					  메소드 이름
	public ResponseEntity getList(@PathVariable("bno") Long bno, @PathVariable("page") int page) {
		// pageNum=3, amount=10
		Criteria cri = new Criteria(page, 10);
		//List<ReplyVO> list = replyService.getList(bno, cri);
		ReplyPageDTO dto = replyService.getListPage(cri, bno);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value="/{rno}") // 한건 조회
	public ResponseEntity get(@PathVariable("rno") Long rno) {
		return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}", produces = MediaType.TEXT_PLAIN_VALUE) //Success 등이 문자열이므로 produces 지정 해주어야 함
	public ResponseEntity remove(@PathVariable("rno") Long rno) {
	//	Map<String, Object>result = new HashMap<String, Object>();
		return replyService.remove(rno) == 1 ?
			new ResponseEntity<>("Success", HttpStatus.OK) :
			new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
								
	@PutMapping(value="/{rno}"
			, consumes = "application/json" //매개값 타입은 consumes //key, value 형식으로 값을 넘기기 위해서.
			, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity modify(@PathVariable("rno") Long rno, @RequestBody ReplyVO vo) {//@RequestBody json 포맷 형식으로 값을 넘기겠다.
		log.info(vo);
		
		int result = replyService.modify(vo);
		return result == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK) :
				new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
