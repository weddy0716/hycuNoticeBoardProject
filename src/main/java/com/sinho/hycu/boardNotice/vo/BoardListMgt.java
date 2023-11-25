package com.sinho.hycu.boardNotice.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class BoardListMgt {
	private Integer seq;		//시퀀스
	private String subject;     //제목
	private String userid;      //등록아이디
	private String contents;    //내용
	private String password;    //비밀번호
	private String createDt;    //생성시간
	private String updateDt;    //업데이트시간
	private String deleteFlag;  //게시글삭제여부
	private String fullName;    //고객성명
}
