package com.study.study_springboot_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration /* spring이 뜰 때 가장 먼저 뜨게 한다 */
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* 권한에 대한 부분. 클라이언트가 자기 권한(role)과 uri를 던지면 authorizeRequests()가 비교해준다 */
        httpSecurity.authorizeRequests()
                /* 유저와 관리자 부터 접근가능 ↓ */
                // .antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                /* 로그인 여부만 판단하는 기능 ↓ */
                .antMatchers("/").authenticated()
                /* 관리자만 접근 가능 ↓ */
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                /* 위에 걸어준 url빼고 전부 다 접근가능 ↓ */
                .anyRequest().permitAll();

        /* 로그인에 대한 부분 */
        httpSecurity.formLogin().loginPage("/loginForm")
                /* 기본 login 페이지로 이동 */
                .loginProcessingUrl("/login")
                /* 루트로 이동 */
                .defaultSuccessUrl("/");
        return httpSecurity.build();/* 서버 띄우기전에 먼저 걸린다 */
    }
}
