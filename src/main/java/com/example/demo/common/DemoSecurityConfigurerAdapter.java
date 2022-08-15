package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DemoSecurityConfigurerAdapter {
    
    // (1) 自前で定義したUserDetailsService
    @Autowired
    UserDetailsService userDetailsService;

    // (2) パスワードハッシュ化する実装をBean登録
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	http.formLogin(login -> login
    	        .usernameParameter("employeeCode")
    	        .passwordParameter("password")
    	        .loginPage("/public/login")
    	        .loginProcessingUrl("/public/login")
    	        .failureUrl("/public/login?error=1")
    	        .defaultSuccessUrl("/", true)
        ).authorizeHttpRequests(authz -> authz
        		.mvcMatchers("/css/**", "/js/**", "/public/**", "/fragment/**").permitAll()
        		.anyRequest().authenticated()
        ).exceptionHandling()
    		.accessDeniedPage("/error/error");
//    	.logout(logout -> logout
//                 .logoutSuccessUrl("/login")
//                 .permitAll()
//         );

        return http.build();
    }

}