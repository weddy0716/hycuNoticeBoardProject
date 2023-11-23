package com.sinho.hycu.boardNotice.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.BoardListMgt;

@Mapper
public interface BoardListMapper {
	int insertNoticeBoardList(BoardListMgt boardmgt);  			 //회원가입시 로그인 정보 저장
	List<BoardListMgt> selectNoticeBoardList(); 				 //게시판목록가져오기
	BoardListMgt selectNoticeBoardDetail(BoardListMgt boardmgt); //게시판목록가져오기
	int UpdateNoticeBoardDetail(BoardListMgt boardmgt); 		 //게시글 업데이트
	int DeleteNoticeBoardList(BoardListMgt boardmgt); 		 	 //게시글 삭제
}
