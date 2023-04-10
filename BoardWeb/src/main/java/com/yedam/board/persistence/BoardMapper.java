package com.yedam.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;

public interface BoardMapper {

//@Select("select * from tbl_board where bno > 0")
	//기본적인 C(reate), R(ead), U(pdate), D(elete) 처리
	
	public List<BoardVO> getList();
	public List<BoardVO> getListWithPaging(Criteria cri);
	public void insertSelectKey(BoardVO board);
	public BoardVO read(long bno);
	public int delete(long bno);
	public int update(BoardVO board);
	public int getTotalCount(Criteria cri);
}
