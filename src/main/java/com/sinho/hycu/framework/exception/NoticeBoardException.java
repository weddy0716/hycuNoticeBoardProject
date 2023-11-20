package com.sinho.hycu.framework.exception;

import org.springframework.http.HttpStatus;

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
