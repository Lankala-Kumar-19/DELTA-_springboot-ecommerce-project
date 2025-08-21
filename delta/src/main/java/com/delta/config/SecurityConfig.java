package com.delta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.delta.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.authorizeHttpRequests(auth->auth
					.requestMatchers("/auth/**").permitAll()
					//.requestMatchers("/products/**","/users/**","/orders/**").permitAll()					
					.requestMatchers("/products/**").hasAnyRole("CUSTOMER","ADMIN")
					.requestMatchers("/users/**").hasRole("ADMIN")
//					.requestMatchers("/orders/id/**").hasRole("ADMIN")
//					.requestMatchers("/orders","/orders/user/**").hasAnyRole("CUSTOMER","ADMIN")
					.requestMatchers("/orders/{id}/status").hasRole("ADMIN")  
	                .requestMatchers("/orders/{id}").hasAnyRole("CUSTOMER","ADMIN") 
	                .requestMatchers("/orders/user/**").hasAnyRole("CUSTOMER","ADMIN") 
	                .requestMatchers("/orders").hasAnyRole("CUSTOMER","ADMIN") 
					.anyRequest().authenticated()
			).formLogin().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
