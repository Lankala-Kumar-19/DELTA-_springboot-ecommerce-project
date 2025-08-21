package com.delta.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.delta.services.CustomUserDetailsService;
import com.delta.services.JwtService;
import com.delta.services.TokenBlacklistService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jser;
	
	@Autowired
	private CustomUserDetailsService cser;
	
	@Autowired
	private TokenBlacklistService tser;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,HttpServletResponse response,FilterChain filterChain) throws ServletException,IOException{
		String authHeader = req.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username=jser.extractUsername(token);
		}
		if(SecurityContextHolder.getContext().getAuthentication()!=null && !tser.isTokenBlacklisted(token)) {
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null && !tser.isTokenBlacklisted(token)) {
			UserDetails userDetails = cser.loadUserByUsername(username);
			
			if(jser.validateToken(token)) {
				System.out.println(userDetails.getAuthorities());				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(req, response);
	}
}
