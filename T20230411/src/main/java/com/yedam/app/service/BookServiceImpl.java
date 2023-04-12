package com.yedam.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.domain.BookVO;
import com.yedam.app.persistence.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class BookServiceImpl implements BookService {
	
	@Setter(onMethod_ = @Autowired)
	private BookMapper bookMapper;

	@Override
	public void register(BookVO book) {
		log.info("등록");
		bookMapper.insert(book);
		
	}

	@Override
	public int bookNoNext() {
		return bookMapper.bookNoNext();		
	}

}
