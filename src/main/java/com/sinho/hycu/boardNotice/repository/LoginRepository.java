package com.sinho.hycu.boardNotice.repository;

import com.sinho.hycu.boardNotice.vo.LoginMgt;

public interface LoginRepository {
	Integer  loginFirstInsert(LoginMgt loginmgt);
	LoginMgt loginCheckerId(LoginMgt loginmgt); //로그인 아이디 체크
	LoginMgt loginCheckerIdPw(LoginMgt loginmgt); //로그인 시도
	Integer  updateFailLoginInfo(LoginMgt loginmgt); //로그인
	Integer  updateLoginInfo(LoginMgt loginmgt);   //로그인정보저장
	Integer updateLoginPasswordInfo(LoginMgt loginmgt);   //로그인 비밀번호 변경

}
