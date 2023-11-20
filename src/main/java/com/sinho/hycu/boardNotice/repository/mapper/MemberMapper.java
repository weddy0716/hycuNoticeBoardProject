package com.sinho.hycu.boardNotice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinho.hycu.boardNotice.vo.Member;

@Mapper
public interface MemberMapper {
	void save(Member member);
}
