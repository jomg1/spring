package com.yedam.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.domain.BookVO;
import com.yedam.app.service.BookService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BookController {
	
	@Setter(onMethod_ = @Autowired)
	private BookService bookService;

	@GetMapping("index")
	public String index() {
		//첫 화면
		return "index";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(BookVO book, RedirectAttributes rttr) {
		bookService.register(book);
		rttr.addFlashAttribute("result", book.getBookNo());
		return "redirect:/register";
	}
	
	@GetMapping("/register")
	public void register(Model model) {
		// 등록 화면
		int next = bookService.bookNoNext();
		System.out.println(next);
		model.addAttribute("bookNo", next);
	}
	
	
	
	
	
}
