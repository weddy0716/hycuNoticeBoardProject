package com.sinho.hycu.boardNotice.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinho.hycu.boardNotice.service.LoginService;
import com.sinho.hycu.boardNotice.vo.LoginMgt;
import com.sinho.hycu.framework.exception.NoticeBoardException;
import com.sinho.hycu.framework.session.SessionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	private LoginService loginService;
	
	public LoginController( LoginService loginService )
	{
		this.loginService = loginService;
	}
	
	@ResponseBody
	@RequestMapping("/login/userlogin.act")
	public String userLogin(HttpServletRequest request , LoginMgt loginmgt , Model model , @RequestHeader(value = "X-Forwarded-For", defaultValue = "") String ipAddress) {
		log.info("###PSH loginmgt : {}" , loginmgt);
		
		log.info("###PSH getRemoteHost : {}" , request.getRemoteHost());
		log.info("###PSH getRemoteAddr : {}" , request.getRemoteAddr());
		log.info("###PSH ipAddress : {}" , ipAddress);
		
		try {
            InetAddress localhost = InetAddress.getLocalHost();
            loginmgt.setLastLoginIp(localhost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
		
		loginService.userLogin(request , loginmgt);
		
		return "1";
	}
	
	@RequestMapping("/login/logout.act")
	public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		SessionManager.removeSession(request);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies
		return "redirect:/";
	}
	
	/**
	 * 에러처리진행
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoticeBoardException.class)
    @ResponseBody
    public ResponseEntity<String> handleCustomException(NoticeBoardException ex) {
        // CustomException이 발생했을 때 처리하는 메서드
		if(!ex.getErrorCode().equals("")) {
			return ResponseEntity.status(ex.getStatus()).body(ex.getMessage() + "||" +ex.getErrorCode());
		}else {
			return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
		}
        
    }
}
