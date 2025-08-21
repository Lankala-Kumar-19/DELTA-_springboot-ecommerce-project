package com.delta.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secretKey;
	
	private final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000;
	
//	private SecretKey getSigningKey() {
//		return Keys.hmacShaKeyFor(secretKey.getBytes());
//	}
	public String generateToken(String subject) {
		Map<String,Object> claims= new HashMap<>();
		return createToken(claims,subject);
	}
	public String createToken(Map<String,Object> claims,String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_EXPIRATION_MS))
				.signWith(SignatureAlgorithm.HS256,secretKey)
				.compact();
	}
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
	public boolean validateToken(String token) {
		return !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	private <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		return claimsResolver.apply(claims);
				
	}
}
