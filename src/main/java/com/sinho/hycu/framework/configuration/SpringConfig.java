package com.sinho.hycu.framework.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinho.hycu.boardNotice.repository.BoardListRepository;
import com.sinho.hycu.boardNotice.repository.LoginRepository;
import com.sinho.hycu.boardNotice.repository.MemberRepository;
import com.sinho.hycu.boardNotice.repository.impl.MybatisMapperBoardListRepository;
import com.sinho.hycu.boardNotice.repository.impl.MybatisMapperLoginRepository;
import com.sinho.hycu.boardNotice.repository.impl.MybatisMapperMemberRepository;
import com.sinho.hycu.boardNotice.repository.mapper.BoardListMapper;
import com.sinho.hycu.boardNotice.repository.mapper.LoginMapper;
import com.sinho.hycu.boardNotice.repository.mapper.MemberMapper;
import com.sinho.hycu.boardNotice.service.BoardListService;
import com.sinho.hycu.boardNotice.service.LoginService;
import com.sinho.hycu.boardNotice.service.MemberService;

import lombok.extern.slf4j.Slf4j;

/**
 * JAVA로 Spring Bean 직접 등록시 작성
 * @author 박신호
 *
 */
@Slf4j
@Configuration
public class SpringConfig {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	BoardListMapper boardListMapper;
	
	@Autowired
	@Qualifier("mybatisDataSource")
	DataSource dataSource;
	
	@Autowired
	@Qualifier("mybatisSqlSessionTemplate")
	SqlSession sqlSession;
	
	@Bean
	public MemberService memberService() {
		log.info("##SpringConfig memberService");
		return new MemberService(memberRepository() , loginRepository());
	}
	
	@Bean
	public LoginService loginService() {
		log.info("##SpringConfig LoginService");
		return new LoginService(loginRepository() , memberRepository());
	}
	
	@Bean
	public BoardListService boardListService() {
		log.info("##SpringConfig LoginService");
		return new BoardListService(boardListRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		log.info("##SpringConfig memberRepository");
		return new MybatisMapperMemberRepository(memberMapper);
	}
	
	@Bean
	public LoginRepository loginRepository() {
		log.info("##SpringConfig memberRepository");
		return new MybatisMapperLoginRepository(loginMapper);
	}
	
	@Bean
	public BoardListRepository boardListRepository() {
		log.info("##SpringConfig boardListRepository");
		return new MybatisMapperBoardListRepository(boardListMapper);
	}
}
