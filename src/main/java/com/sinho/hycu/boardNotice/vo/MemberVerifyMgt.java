package com.sinho.hycu.boardNotice.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class MemberVerifyMgt {
	private Integer seq;		  //시퀀스
	private String userid;        //사용자ID
	private String verifyCode;    //인증코드(SHA-256)
	private String registDt;      //비밀번호
	private String sendEmail;     //발송이메일
	private String rawVerifyCode; //인증코드6자리(평문)
}
