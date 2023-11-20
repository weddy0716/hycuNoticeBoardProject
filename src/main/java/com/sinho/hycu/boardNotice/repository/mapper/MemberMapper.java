package com.sinho.hycu.boardNotice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.Member;

@Mapper
public interface MemberMapper {
	int memberJoin(Member member);
	Member findByUserID(Member member);
	Member findByEmail(Member member);
}
