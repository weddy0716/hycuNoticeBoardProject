package com.sinho.hycu.framework.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
/**
 * Interceptor 작성
 * @author 박신호
 *
 */
public class HttpInterceptor implements HandlerInterceptor{
	
	//Controller 호출전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("###PSH Controller 호출전 url 검사 getRequestURL -> {}" , "["+request.getRequestURL() + "]");
		List<HashMap> requestHeaderInfo = new ArrayList<HashMap>();
		Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = (String)headerNames.nextElement();
            String value = request.getHeader(name);
            HashMap map = new HashMap();
            map.put(name, value);
            requestHeaderInfo.add(map);
        }
         
        log.info("###PSH HttpInterceptor requestHeaderInfo X-FORWARDED-FOR : {} || getRemoteIP : {} || requestURL : {}", request.getHeader("X-FORWARDED-FOR") , request.getRemoteAddr() , request.getRequestURL());
        log.info("###PSH HttpInterceptor requestHeaderInfo size : {} || data : {} ", requestHeaderInfo.size() , requestHeaderInfo);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("###PSH Controller 호출후 -> {}" , "[" + request + "]");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("###PSH Controller 후 url 검사 afterCompletion -> {}" , "[" + response.getCharacterEncoding() + "]");
	}
}
