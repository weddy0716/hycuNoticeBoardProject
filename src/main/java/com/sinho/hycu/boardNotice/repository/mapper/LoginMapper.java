package com.sinho.hycu.boardNotice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.LoginMgt;

@Mapper
public interface LoginMapper {
	int loginFirstInsert(LoginMgt loginmgt);  //회원가입시 로그인 정보 저장
	LoginMgt loginCheckerId(LoginMgt loginmgt); //로그인
	LoginMgt loginCheckerIdPw(LoginMgt loginmgt); //로그인
	int updateFailLoginInfo(LoginMgt loginmgt); //로그인
	int updateLoginInfo(LoginMgt loginmgt);   //로그인정보저장
}
