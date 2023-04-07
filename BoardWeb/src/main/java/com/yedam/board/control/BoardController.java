package com.yedam.board.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.PageDTO;
import com.yedam.board.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;

	@RequestMapping("list")
	public void list(Model model, Criteria cri) { // 처리값 model에 담아둠
		log.info("컨트롤 .. 목록조회");
//		List<BoardVO> list = boardService.getList();
		List<BoardVO> list = boardService.getList(cri);
		int total = boardService.getTotalCount(cri);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
		// /WEB-INF/views/board/list.jsp
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(BoardVO board, RedirectAttributes model) {//Model과 비슷한 클래스인 RedirectAttributes 사용 (Model이 값을 가지고 오지 못 할 때)
		log.info("컨트롤 .. 등록");
		// 등록 처리 후 목록 이동
		boardService.register(board);
		model.addFlashAttribute("result", board.getBno()); //RedirectAttributes 쓸 때의 메소드는 addFlashAttribute<=값을 일회성으로 쓸 때 
		
		return "redirect:/board/list"; // response.sendRedirect();
	}
	
	@GetMapping("register")
	public void register() {
		// 등록 화면
	}
	
	@GetMapping({"get","modify"})//{}배열 형식으로 get, modify 요청이 들어와도 해당 펭지ㅣ를 열어줌
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) { // 단건 조회
		log.info("컨트롤 .. 단건 조회 / 수정 이동");
		model.addAttribute("board", boardService.get(bno));
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) { // 수정
		log.info("컨트롤 .. 수정");
		log.info(cri);
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result","Success");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		// response.sendRedirect(); forwarding이 아니고 RedirectAttributes 사용해야함
		return "redirect:/board/list"; 
	}
	
	@PostMapping("remove")
	public String remove(Long bno, Criteria cri, RedirectAttributes rttr) { // 삭제
		log.info("컨트롤 .. 삭제");
		if(boardService.remove(bno)) {
			rttr.addAttribute("result","Success");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; // response.sendRedirect();
	}
}
