package com.yedam.board.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage; //현재 페이지를 기준으로 시작 페이지 11...15...20 중 11
	private int endPage;
	private boolean prev, next;
	
	private int total; //페이징을 위한 토탈 건수
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		// 계산 시 endPage부터 하는 것이 편함
		// if 현재 13page 2*10(2page, 10개씩)
		this.endPage = (int) (Math.ceil(cri.getPageNum()/10.0)) * 10; // Math.ceil => double 타입으로 들어옴
		this.startPage = this.endPage -9; // 11..13..20
		
		int realEnd = (int) (Math.ceil((total*1.0)/cri.getAmount())); // 전체 건수에 대한 실제 마지막 페이지 계산
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1; // 이전 페이지가 있다는 의미
		this.next = this.endPage < realEnd; // 다음 페이지가 있다는 의미
	}
}
