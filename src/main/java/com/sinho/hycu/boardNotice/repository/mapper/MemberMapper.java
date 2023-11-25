package com.sinho.hycu.boardNotice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.boardNotice.vo.MemberVerifyMgt;

@Mapper
public interface MemberMapper {
	int memberJoin(Member member); //회원가입
	Member findByUserID(Member member); //ID중복검사
	Member findByEmail(Member member); //이메일중복검사
	Member findByUserInfo(Member member); //로그인후사용자정보조회
	Member findByUserVerifyInfo(Member member); //비밀번호찾기를위한 사용자 검사
	int insertMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt); //비밀번호찾기를위한 인증코드저장
	MemberVerifyMgt selectMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt); //입력된 인증코드 검증
	int updateMemberPasswordReset(Member member); //비밀번호재설정
	int updateMemberInfo(Member member); //회원정보수정
}
