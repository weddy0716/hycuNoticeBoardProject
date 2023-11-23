package com.sinho.hycu.boardNotice.repository.impl;

import com.sinho.hycu.boardNotice.repository.MemberRepository;
import com.sinho.hycu.boardNotice.repository.mapper.MemberMapper;
import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.boardNotice.vo.MemberVerifyMgt;

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
public class MybatisMapperMemberRepository implements MemberRepository {
	
	private final MemberMapper memberMapper;
	
	//private SqlSession sqlSession;
	
	public MybatisMapperMemberRepository(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@Override
	public Member memberJoin(Member member) {
		// TODO Auto-generated method stub
		int result = this.memberMapper.memberJoin(member);
		member.setSeq(result);
		return member;
	}

	@Override
	public Member findByName(Member member) {
		Member result = this.memberMapper.findByUserID(member);
		return result;
	}
	
	@Override
	public Member findByEmail(Member member) {
		Member result = this.memberMapper.findByEmail(member);
		return result;
	}
	
	@Override
	public Member findByUserInfo(Member member) {
		Member result = this.memberMapper.findByUserInfo(member);
		return result;
	}

	@Override
	public Member findByUserVerifyInfo(Member member) {
		Member result = this.memberMapper.findByUserVerifyInfo(member);
		return result;
	}

	/**
	 * 인증코드발송저장
	 */
	@Override
	public int insertMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt) {
		int result = this.memberMapper.insertMemberVerifyMgt(memberVerifyMgt);
		return result;
	}

	@Override
	public MemberVerifyMgt selectMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt) {
		MemberVerifyMgt result = this.memberMapper.selectMemberVerifyMgt(memberVerifyMgt);
		return result;
	}
}
