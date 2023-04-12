package com.yedam.app.persistence;

import com.yedam.app.domain.BookVO;

public interface BookMapper {
	
	public int insert(BookVO book);
	public int bookNoNext();

}
