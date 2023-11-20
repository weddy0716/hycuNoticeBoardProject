package com.sinho.hycu.framework.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@MapperScan(value = "com.sinho.hycu.boardNotice.repository.mapper", sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class MybatisConfiguration {
	
	/**
	 * 1.HikariConfig 생성
	 * 2.Datasource HikariDataSource로 생성 및 config 주입
	 * 3.sqlSessionFactory 작성
	 * 4.SqlSessionTemplate 작성
	 * @return
	 */
	@Primary
	@Qualifier("HikariConfig")
	@Bean
	//application.yml 프로퍼티 경로 명시
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig() {
		log.info("##SampleStudyDBMybatisConfiguration hikariConfig");
        return new HikariConfig();
    }
	
	@Primary
	@Bean(name = "mybatisDataSource")
    public DataSource dataSource() throws Exception {
		log.info("##SampleStudyDBMybatisConfiguration dataSource");
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }
	
	@Bean(name="mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource studyDBmybatisDataSource, ApplicationContext applicationContext) throws Exception{

		//세션 생성 시, 빌드된 DataSource를 세팅하고 SQL문을 관리할 mapper.xml의 경로를 알려준다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(studyDBmybatisDataSource);
		
		// Spring Boot 전용 VFS 사용하도록 지정(로컬IDE에서는 문제가없으나 서버상에 동작할때 alias를 못찾는 현상이 있다고함.
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);  
		//alias 설정 -> 프로퍼티로 뺴도록 하자.
		
		sqlSessionFactoryBean.setTypeAliasesPackage("com.sinho.hycu.boardNotice.vo"); 
		
		//mybatis 설정파일
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/spring-mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/mybatis/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Primary
	@Bean(name="mybatisSqlSessionTemplate")
	public SqlSessionTemplate mybatisSqlSessionTemplate(@Qualifier("mybatisSqlSessionFactory")SqlSessionFactory mybatisSqlSessionFactory) throws Exception{

		return new SqlSessionTemplate(mybatisSqlSessionFactory);
	}

}
