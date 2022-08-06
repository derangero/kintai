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
    	        .loginPage("/login")
    	        .loginProcessingUrl("/login")
    	        .failureUrl("/login?error=1")
    	        .defaultSuccessUrl("/", true)
        ).authorizeHttpRequests(authz -> authz
    			//register は直リンクしない
                //管理者ユーザー向けのアクセスパスは、管理者権限をもつユーザーのみアクセス可能にする
        		.mvcMatchers("/css/**", "/js/**", "/login").permitAll()
                .anyRequest().authenticated()
        );
//    	.logout(logout -> logout
//                 .logoutSuccessUrl("/login")
//                 .permitAll()
//         );

        return http.build();
    }

}