package com.study.study_springboot_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration /* spring이 뜰 때 가장 먼저 뜨게 한다 */
public class SecurityConfiguration {
    // None using csrf protection
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* 권한에 대한 부분. 클라이언트가 자기 권한(role)과 uri를 던지면 authorizeRequests()가 비교해준다 */

        httpSecurity.csrf().disable();// None using csrf protection

        httpSecurity.authorizeRequests()
                /* 유저와 관리자 부터 접근가능 ↓ */
                // .antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                /* 로그인 여부만 판단하는 기능 ↓ */
                // .antMatchers("/").authenticated()
                /* 관리자만 접근 가능 ↓ */
                // .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/admin").authenticated()
                .antMatchers("/manager/*").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
                /* 위에 걸어준 url빼고 전부 다 접근가능(로그인) ↓ */
                .anyRequest().permitAll();

        /* 로그인에 대한 부분 */
        httpSecurity.formLogin().loginPage("/loginForm")
                .failureUrl("/loginForm?fail=true")
                /*
                 * form태그가 post방식이므로 아래에서 spring의 login에게 post방식으로 던져주는것. spring이 ID와 password를
                 * 알아서 비교해준다. 아래.loginProcessingUrl()은 안 써줘도 스프링
                 * 기본내장 로그인이 자동으로 걸린다. .defaultSuccessUrl()도 똑같다 *
                 */
                .loginProcessingUrl("/login")
                /* 루트로 이동 */
                .defaultSuccessUrl("/");

        return httpSecurity.build();/* 서버 띄우기전에 먼저 걸린다 */
    }

    @Bean
    /*
     * interface라서 상속받아 내가 원하는대로 암호화 할 수 있지만 현재는 하지 않고
     * 기본 spring암호화를 사용한 상태.
     */
    public BCryptPasswordEncoder encoderPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
