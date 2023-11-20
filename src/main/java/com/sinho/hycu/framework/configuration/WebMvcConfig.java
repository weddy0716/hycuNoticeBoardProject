package com.sinho.hycu.framework.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sinho.hycu.framework.interceptor.HttpInterceptor;

/**
 * Controller 호출전 작성한 Interceptor가 호출되도록 환경설정을 진행한다.
 * @author 박신호
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor())
		.order(1)
        .addPathPatterns("/*");
        //.excludePathPatterns("/danbplus"); // 해당 경로는 인터셉터가 가로채지 않는다.

		// 로그인 세션 체크
//		registry.addInterceptor(new LoginInterceptor()) // 인터셉터 등록
//		.order(2) // 동작순서
//		.addPathPatterns("/*") // 인터셉터를 적용할 URL패턴 설정
//		.excludePathPatterns("/login/*", "/css/**", "/*.ico", "/error"); // 인터셉터를 제외할 URL패턴 등록
	}
}
