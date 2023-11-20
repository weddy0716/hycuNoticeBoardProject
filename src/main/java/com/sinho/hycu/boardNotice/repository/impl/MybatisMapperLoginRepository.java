package com.sinho.hycu.boardNotice.repository.impl;

import com.sinho.hycu.boardNotice.repository.LoginRepository;
import com.sinho.hycu.boardNotice.repository.mapper.LoginMapper;
import com.sinho.hycu.boardNotice.vo.LoginMgt;
import com.sinho.hycu.boardNotice.vo.Member;

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
public class MybatisMapperLoginRepository implements LoginRepository {
	
	private final LoginMapper loginMapper;
	
	//private SqlSession sqlSession;
	
	public MybatisMapperLoginRepository(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	@Override
	public Integer loginFirstInsert(LoginMgt loginmgt) {
		// TODO Auto-generated method stub
		int result = this.loginMapper.loginFirstInsert(loginmgt);
		return result;
	}

	@Override
	public LoginMgt loginCheckerId(LoginMgt loginmgt) {
		// TODO Auto-generated method stub
		return this.loginMapper.loginCheckerId(loginmgt);
	}
	
	@Override
	public LoginMgt loginCheckerIdPw(LoginMgt loginmgt) {
		// TODO Auto-generated method stub
		return this.loginMapper.loginCheckerIdPw(loginmgt);
	}

	@Override
	public Integer updateLoginInfo(LoginMgt loginmgt) {
		int result = this.loginMapper.updateLoginInfo(loginmgt);
		return result;
	}

	@Override
	public Integer updateFailLoginInfo(LoginMgt loginmgt) {
		int result = this.loginMapper.updateFailLoginInfo(loginmgt);
		return result;
	}
}
