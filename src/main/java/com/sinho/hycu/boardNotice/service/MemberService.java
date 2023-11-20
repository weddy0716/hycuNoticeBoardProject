package com.sinho.hycu.boardNotice.service;

import org.springframework.transaction.annotation.Transactional;

import com.sinho.hycu.boardNotice.repository.MemberRepository;
import com.sinho.hycu.boardNotice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		log.info("###MemberService");
		this.memberRepository = memberRepository;
	}
	
	@Transactional
	public Integer join(Member member) {
		/*Optional<Member> result = memberRepository.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원 입니다.");
		});
		*/
		
		//리팩토링(extract Method -> Alt + shift + m -> 함수명 지정
		
		//validateDuplicateMember(member); //중복확인
		memberRepository.memberJoin(member);
		return member.getSeq();
	}
}
