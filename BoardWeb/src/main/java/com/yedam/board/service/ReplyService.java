package com.yedam.board.service;

import java.util.List;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyVO;

public interface ReplyService {
	public int register(ReplyVO vo); // 등록
	public ReplyVO get(Long rno); // 한 건 조회
	public int modify(ReplyVO vo); // 수정
	public int remove(Long rno); //삭제
	public List<ReplyVO> getList(Long bno, Criteria cri); // 댓글 목록

}
