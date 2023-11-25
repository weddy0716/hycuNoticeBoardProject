package com.sinho.hycu.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 게시판 프로젝트 공통 에러 클래스
 * 업무에서 Message와 errorCode를 Customizing하여 사용자에게 보여주도록 한다.
 * @author weddy
 *
 */
public class NoticeBoardException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String errorCode;

    public NoticeBoardException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    
    public NoticeBoardException(String message, HttpStatus status , String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
