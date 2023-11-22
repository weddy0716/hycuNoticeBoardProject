package com.sinho.hycu.boardNotice.repository.impl;

import java.util.List;

import com.sinho.hycu.boardNotice.repository.BoardListRepository;
import com.sinho.hycu.boardNotice.repository.mapper.BoardListMapper;
import com.sinho.hycu.boardNotice.vo.BoardListMgt;

import lombok.extern.slf4j.Slf4j;

/**
 * 마이바티스 구현 방식은 2가지가 존재.
 * 마이바티스 3.0 이전 방식 -> SessionTemplate 방식
 * 마이바티스 3.0 이후 방식 -> MapperInterface 방식
 * 해당 클래스는 마이바티스 sessionTemplate 방식의 Repository 작성.
 * SqlSession 선언부 공통 작성을 어떻게해야하나 고민중.
 * @author 박신호
 */
@Slf4j
public class MybatisMapperBoardListRepository implements BoardListRepository {
	
	private final BoardListMapper boardListMapper;
	
	public MybatisMapperBoardListRepository(BoardListMapper boardListMapper) {
		this.boardListMapper = boardListMapper;
	}

	@Override
	public int insertNoticeBoardList(BoardListMgt boardmgt) {
		// TODO Auto-generated method stub
		int result = this.boardListMapper.insertNoticeBoardList(boardmgt);
		return result;
	}

	@Override
	public List<BoardListMgt> selectNoticeBoardList() {
		List<BoardListMgt> boardList = this.boardListMapper.selectNoticeBoardList();
		return boardList;
	}

	@Override
	public BoardListMgt selectNoticeBoardDetail(BoardListMgt boardmgt) {
		BoardListMgt boardDetail = this.boardListMapper.selectNoticeBoardDetail(boardmgt);
		return boardDetail;
	}
}
