package com.wc.weconnect.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
	
	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt = Jwts.builder()
					.issuer("We_Connect")
					.issuedAt(new Date())
					.expiration(new Date(new Date().getTime()+86400000))
					.claim("email", auth.getName())
					.signWith(key)
					.compact();
		
		return jwt;
	}
	
	// get Email from the generated token
	public static String getEmailFromJwtToken(String jwt) {
		// Bearer token -----> so we have to separate the token 
		jwt = jwt.substring(7);// length of Bearer with one space 
		
		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
		
		// get email
		String email = String.valueOf(claims.get("email"));
		
		return email;
	}
	
	// From jwt token we take out user
}

