package com.sinho.hycu.boardNotice.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.sinho.hycu.boardNotice.repository.LoginRepository;
import com.sinho.hycu.boardNotice.repository.MemberRepository;
import com.sinho.hycu.boardNotice.vo.LoginMgt;
import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.common.util.PasswordCrypto;
import com.sinho.hycu.framework.exception.NoticeBoardException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberService  {
	
	private final MemberRepository memberRepository;
	private final LoginRepository loginRepository;
	
	public MemberService(MemberRepository memberRepository , LoginRepository loginRepository) {
		log.info("###MemberService");
		this.memberRepository = memberRepository;
		this.loginRepository = loginRepository;
	}
	
	@Transactional
	public Integer memberJoin(Member member) {
		//리팩토링(extract Method -> Alt + shift + m -> 함수명 지정
		validateDuplicateMember(member); //중복확인
		
		String password = member.getPassword();
		member.setPassword(PasswordCrypto.SHA256(password));
		memberRepository.memberJoin(member); //회원가입테이블 저장
		//로그인정보테이블 저장
		loginRepository.loginFirstInsert(new LoginMgt().setUserid(member.getUserid()).setPassword(member.getPassword()));
		return member.getSeq();
	}
	
	/**
	 * 회원가입시 아이디,이메일중복체크를 진행한다.
	 * @param member
	 */
	private void validateDuplicateMember(Member member) {
		
		Optional<Member> searchUserIdResult = Optional.ofNullable(memberRepository.findByName(member));
		searchUserIdResult.ifPresent(m -> {
			//throw new IllegalStateException("이미 존재하는 회원 입니다.[0]");
			throw new NoticeBoardException("이미 존재하는 회원 입니다.[0]", HttpStatus.INTERNAL_SERVER_ERROR);
		});
		
		Optional<Member> searchEmailResult = Optional.ofNullable(memberRepository.findByEmail(member));
		searchEmailResult.ifPresent(m -> {
			throw new NoticeBoardException("이미 존재하는 회원 입니다.[1]", HttpStatus.INTERNAL_SERVER_ERROR);
		});
	}
}
