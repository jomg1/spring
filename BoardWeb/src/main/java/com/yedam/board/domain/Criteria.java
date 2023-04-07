package com.yedam.board.domain;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum; //페이지 수
	private int amount; //한페이지당 보여줄 건 수
	private String type; // T, TW, TWC
	private String keyword;
	
	private String[] typeArr; // {T} or {TW} or {TWC} // getTypeArr() 이걸 위해서 보여주려고 쓴 것... 없어도 sql 처리는 가능
	
	public Criteria() {
		this.pageNum=1;
		this.amount=10;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() { // mapper.xml과 이름이 꼭 같아야함
		return type == null ? new String[] {} : type.split("");
	}
	
}
