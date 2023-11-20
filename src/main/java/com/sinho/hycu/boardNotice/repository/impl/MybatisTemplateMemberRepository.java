package com.sinho.hycu.boardNotice.repository.impl;

import org.apache.ibatis.session.SqlSession;

import com.sinho.hycu.boardNotice.repository.MemberRepository;
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
public class MybatisTemplateMemberRepository implements MemberRepository {
	
	private SqlSession sqlSession;
	
	public MybatisTemplateMemberRepository(SqlSession sqlsession) {
		this.sqlSession = sqlsession;
	}
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		this.sqlSession.insert("SampleSource.save" , member);
		log.debug("###PSH save member {}" , member.getSeq());
		//member.setId(result);
		return member;
	}

}
