package com.sinho.hycu.boardNotice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.BoardListMgt;

@Mapper
public interface BoardListMapper {
	int insertNoticeBoardList(BoardListMgt boardmgt);  //회원가입시 로그인 정보 저장
}
