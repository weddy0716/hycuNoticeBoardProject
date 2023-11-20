package com.sinho.hycu.boardNotice.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class LoginMgt {
	private String userid;		  //사용자ID
	private String password;	  //비밀번호
	private String lastLoginDt; //마지막로그인날짜
	private String passErrorCnt;  //비밀번호오류횟수
	private String lastLoginIp;  //마지막로그인IP
	private String lastFailLoginDt;  //마지막로그인IP
}
