package com.sinho.hycu.framework.interceptor;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sinho.hycu.framework.session.SessionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
/**
 * Interceptor 작성
 * @author 박신호
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	// Controller 호출 전 [ preHandle → Controller ]
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// preHandle
		String loginId = (String) SessionManager.getSession(request, "loginId"); // 세션에서 로그인ID 조회
		if(loginId == null) {
			// 로그인ID 미존재
			HashMap<String, String> param = new HashMap<String, String>();
			
			String url = request.getRequestURI(); // 요청URL
			String queryStr = request.getQueryString(); 
			String preUrl = "";
			
			if(url == null) {
				preUrl = url;
			} else {
				preUrl = (queryStr == null) ? url : url+"?"+queryStr;
			}
			
			// 직전 URL을 세션에 저장
			param.put("preUrl", preUrl);
			SessionManager.setSession(request, param);
			
			// 세션 없을때 뒤로가기 방지
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			response.setHeader("Expires", "0"); // Proxies
			
			// 세션이 없으므로 로그인화면으로 이동
			response.sendRedirect("/login/loginForm");
			return false;
		} else {
			// 로그인 ID존재
		}
         
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
