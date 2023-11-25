package com.sinho.hycu.boardNotice.repository;

import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.boardNotice.vo.MemberVerifyMgt;

public interface MemberRepository {
	Member memberJoin(Member member);
	Member findByName(Member member);
	Member findByEmail(Member member);
	Member findByUserInfo(Member member);
	Member findByUserVerifyInfo(Member member); //비밀번호찾기를위한 사용자 검사
	int insertMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt);
	MemberVerifyMgt selectMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt); //인증코드검증
	int updateMemberPasswordReset(Member member); //비밀번호재설정
	int updateMemberInfo(Member member); //회원정보수정
}
