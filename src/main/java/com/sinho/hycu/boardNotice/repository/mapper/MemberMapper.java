package com.sinho.hycu.boardNotice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.boardNotice.vo.MemberVerifyMgt;

@Mapper
public interface MemberMapper {
	int memberJoin(Member member);
	Member findByUserID(Member member);
	Member findByEmail(Member member);
	Member findByUserInfo(Member member);
	Member findByUserVerifyInfo(Member member); //비밀번호찾기를위한 사용자 검사
	
	int insertMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt);
	
	MemberVerifyMgt selectMemberVerifyMgt(MemberVerifyMgt memberVerifyMgt);
	
	int updateMemberPasswordReset(Member member);
}
