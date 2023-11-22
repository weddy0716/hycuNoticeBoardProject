package com.sinho.hycu.boardNotice.repository;

import java.util.List;

import com.sinho.hycu.boardNotice.vo.BoardListMgt;

public interface BoardListRepository {
	int insertNoticeBoardList(BoardListMgt boardmgt);
	List<BoardListMgt> selectNoticeBoardList(); //게시판목록가져오기
	BoardListMgt selectNoticeBoardDetail(BoardListMgt boardmgt); //게시판목록가져오기
}
