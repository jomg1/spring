package com.yedam.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyVO;

public interface ReplyMapper {

	//기본적인 C(reate), R(ead), U(pdate), D(elete) 처리
	public int insert(ReplyVO vo);
	public ReplyVO read(Long rno); // 댓글번호 조회
	public int delete(Long rno); // 댓글 삭제
	public int update(ReplyVO vo); //수정
	public List<ReplyVO> getListWithPaging(@Param("bno") Long bno, @Param("cri") Criteria cri); // 글번호(페이지, 건수) //test page에서 파라미터 타입을 읽어오지 못함으로 @Param 붙임.
	public int getCountByBno(Long bno); // 댓글의 전체 건수
	
}
