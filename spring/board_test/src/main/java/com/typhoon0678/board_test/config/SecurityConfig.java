package com.typhoon0678.board_test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/h2-console/**",
                        "/mapper/**"
                )
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                "/",
                                "/member/signup",
                                "/member/signin")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin((auth) -> auth
                        .loginPage("/member/signin")
                        .loginProcessingUrl("/member/signin")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .build();
    }

}