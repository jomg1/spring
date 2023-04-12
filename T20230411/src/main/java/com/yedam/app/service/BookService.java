package com.yedam.app.service;

import com.yedam.app.domain.BookVO;

public interface BookService {
	public void register (BookVO book);
	public int bookNoNext();
}
