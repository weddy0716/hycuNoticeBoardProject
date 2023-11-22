package com.sinho.hycu;

import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.sinho.hycu.framework.session.SessionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class HycuBoardNoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HycuBoardNoticeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

//			log.info("### 스프링부트 러너");
//			log.info("### 스프링 부트 애플리케이션 구동시 시작되는 프로그램을 살펴보자");

			String[] beanNames = ctx.getBeanDefinitionNames();
			for (String beanName : beanNames) {
				//log.info("###beanName:[{}" , beanName + "]");
			}

		};
	}
	
	
	@Bean
    public HttpSessionListener httpSessionListener() {
    	return new SessionManager();
    }
}
