package com.sinho.hycu.boardNotice.service;

import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.sinho.hycu.boardNotice.repository.LoginRepository;
import com.sinho.hycu.boardNotice.repository.MemberRepository;
import com.sinho.hycu.boardNotice.vo.LoginMgt;
import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.common.util.PasswordCrypto;
import com.sinho.hycu.framework.exception.NoticeBoardException;
import com.sinho.hycu.framework.session.SessionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginService {
	private final LoginRepository loginRepository;
	private final MemberRepository memberRepository;
	
	public LoginService(LoginRepository loginRepository , MemberRepository memberRepository) {
		log.info("###LoginService");
		this.loginRepository  = loginRepository;
		this.memberRepository = memberRepository;
	}
	
	public LoginMgt userLogin(HttpServletRequest request , LoginMgt loginmgt) {
		//Password는 암호화할것.
		String password = loginmgt.getPassword();
		String requestIP = loginmgt.getLastLoginIp();
		loginmgt.setPassword(PasswordCrypto.SHA256(password));
		Optional<LoginMgt> loginIdInfo = Optional.ofNullable(loginRepository.loginCheckerId(loginmgt)); //로그인ID체크
		log.info("###PSH loginIdInfo Check : {}" , loginIdInfo);
		
		if(loginIdInfo.isEmpty()) {
			throw new NoticeBoardException("로그인정보가 없습니다.[0]", HttpStatus.INTERNAL_SERVER_ERROR , "COMM_0001");
		}
		
		String passwordErrorCntStr = loginIdInfo.get().getPassErrorCnt() == null ? "0" : loginIdInfo.get().getPassErrorCnt();
		int passErrorCnt = Integer.parseInt(passwordErrorCntStr);
		
		Optional<LoginMgt> loginIdPwInfo = Optional.ofNullable(loginRepository.loginCheckerIdPw(loginmgt)); //로그인진행
		if(loginIdPwInfo.isEmpty()) {
			//비밀번호오류횟수 추가
			if(passErrorCnt > 4) {
				throw new NoticeBoardException("비밀번호오류횟수를 초과했습니다.비밀번호찾기페이지로 이동합니다." ,  HttpStatus.INTERNAL_SERVER_ERROR , "COMM_0002");
			}
			loginIdInfo.get().setPassErrorCnt(String.valueOf(passErrorCnt+1)).setLastLoginIp(requestIP);
			log.info("###PSH loginIdInfo result ### : {}" , loginIdInfo.get());
			int result = loginRepository.updateFailLoginInfo(loginIdInfo.get());
			log.info("###PSH updateFailLoginInfo result : {}" , result);
			
			throw new NoticeBoardException("비밀번호가 틀립니다.오류횟수["+(passErrorCnt+1)+"]" , HttpStatus.INTERNAL_SERVER_ERROR , "COMM_0003");
		}else {
			SessionManager.removeSession(request);
			
			Member member = new Member();
			member.setUserid(loginmgt.getUserid()).setPassword(loginmgt.getPassword());
			Optional<Member> userinfo = Optional.ofNullable(memberRepository.findByUserInfo(member));
			
			//loginmgt.setLastLoginIp(passwordErrorCntStr);
			
			int updateLoginTime = loginRepository.updateLoginInfo(loginmgt);
			//로그인테이블에 로그인 시간 저장진행
			
			HashMap<String,String> loginMap = new HashMap<String,String>();
			loginMap.put("userid"	, userinfo.get().getUserid());
			loginMap.put("email"	, userinfo.get().getEmail());
			loginMap.put("fullName"		, userinfo.get().getFullName());
			loginMap.put("lastLoginDt"	, loginIdPwInfo.get().getLastLoginDt());
			SessionManager.setSession(request, loginMap);
			SessionManager.setSession(request, "userinfo" , userinfo.get());
		}
		log.info("###sessionManager Login : {}" , SessionManager.getSession(request, "userid"));
		
		return loginmgt;
	}
}
