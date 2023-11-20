package com.sinho.hycu.boardNotice.vo;

import lombok.Data;

@Data
public class Member {
	private Integer seq;
	private String userId;
	private String password;
	private String email;
	private String fullName;
}
