package com.sinho.hycu.framework.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessionManager implements HttpSessionListener {

    public static HttpSession session;
    
    @Value("${server.servlet.session.timeout}")
    private int sessionTime;
    
	public static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();
	
	@Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(sessionTime); // 세션시간 설정
        sessions.put(se.getSession().getId(), se.getSession());
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	if(sessions.get(se.getSession().getId()) != null) {
    		sessions.get(se.getSession().getId()).invalidate();
    		sessions.remove(se.getSession().getId());
    	}
    }
    
    public static Object getSession(HttpServletRequest request, String key) {
    	session = request.getSession();
    	return session.getAttribute(key);
    }

	public static void setSession(HttpServletRequest request, HashMap<String, String> hashMap) {
		session = request.getSession();
		for (Entry<String, String> entity : hashMap.entrySet()) {
			session.setAttribute(entity.getKey(), entity.getValue());
		}
	}
	
	public static void setSession(HttpServletRequest request, String key , Object object) {
		session = request.getSession();
		session.setAttribute(key, object);
	}
	
	public static void removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) { // 세션정보가 존재할 경우
			request.getSession().invalidate(); // 세션에 등록된 정보 삭제
		}
	}
}