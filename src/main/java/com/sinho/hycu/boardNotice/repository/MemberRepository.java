package com.sinho.hycu.boardNotice.repository;

import com.sinho.hycu.boardNotice.vo.Member;

public interface MemberRepository {
	Member memberJoin(Member member);
	Member findByName(Member member);
	Member findByEmail(Member member);
	Member findByUserInfo(Member member);
}
