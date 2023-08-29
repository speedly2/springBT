package com.ses.test3.security;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ses.test3.controller.SseController;

import lombok.extern.slf4j.Slf4j;

/**
 * Security 설정
 */
@Slf4j
@Configuration
public class WebSecurityConfig {
    @Autowired
    private DataSource dataSource;

    //설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/",
        		"/member/join",
        		"/google/login",
        		
        		"/saveEvent",
                "/image/**",
                "/css/**",
                "/js/**").permitAll()		//설정한 리소스의 접근을 인증절차 없이 허용
        .anyRequest().authenticated()   	//위의 경로 외에는 모두 로그인을 해야 함
        .and()
        .formLogin()						//일반적인 폼을 이용한 로그인 처리/실패 방법을 사용
        .loginPage("/member/loginForm")		//시큐리티에서 제공하는 기본 폼이 아닌 사용자가 만든 폼 사용
        .loginProcessingUrl("/member/login").permitAll()	//인증 처리를 하는 URL을 설정. 로그인 폼의 action으로 지정
        .usernameParameter("memberid")		//로그인폼의 아이디 입력란의 name
        .passwordParameter("memberpw")		//로그인폼의 비밀번호 입력란의 name
        .and()
        .logout()
        .addLogoutHandler((request, response, authentication) -> { 
        	//로그아웃처리시 핸들러를 추가하여 sse목록에서 접속유저 아이디를 삭제
        	String id = authentication.getName();
            SseController.getClients().remove(id);
            
            //출력
            SseController.getClients().entrySet().stream().map(Map.Entry::getKey).forEach(k -> log.debug("※ CLIENTS_ID: {}", k));
            
            log.debug("※ 로그아웃 securityConfig_removeId: {}" ,id);
        })  // 로그아웃 핸들러 추가
        .logoutUrl("/member/logout")
        .logoutSuccessUrl("/").permitAll()	//로그아웃시에 이동할 경로
        .and()
        .cors()
        .and()
        .httpBasic();
        
        return http.build();
    }

    //인증을 위한 쿼리
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        // 인증 (로그인)
        .usersByUsernameQuery(
        		"select memberid username, memberpw password, enabled " +
                "from sseTest2 " +
                "where memberid = ?")
        // 권한
        .authoritiesByUsernameQuery(
        		"select memberid username, rolename role_name " +
                "from sseTest2 " +
                "where memberid = ?");
    }

    // 단방향 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
