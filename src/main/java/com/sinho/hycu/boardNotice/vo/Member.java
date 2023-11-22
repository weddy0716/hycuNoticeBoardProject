package com.sinho.hycu.boardNotice.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Member {
	private Integer seq;
	private String userid;
	private String password;
	private String email;
	private String fullName;
	private String passErrorCnt; //비밀번호오류횟수
}
