package com.yedam.app.domain;

import lombok.Data;

@Data
public class BookVO {
	/*
	 * book_no number not null, book_name varchar2(50) not null, book_coverimg
	 * varchar2(20), book_date date, book_price number, book_publisher varchar2(50),
	 * book_info varchar2(2000)
	 */
	
	int bookNo;
	String bookName;
	String bookCoverimg;
	String bookDate;
	int bookPrice;
	String bookPublisher;
	String bookInfo;
}
