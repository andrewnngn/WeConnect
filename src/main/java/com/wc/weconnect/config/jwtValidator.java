package com.WeConnect.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// whenever we access secure endpoint then we give token to header so how do we access this token so we have to get the token 
		
		String jwt = request.getHeader(JwtConstant.jwt_HEADER);
		
		if(jwt!=null) {
			try {
				String email  = JwtProvider.getEmailFromJwtToken(jwt);
				
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(email,null, authorities);
				
				// set this authentication in SecurityContext
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				throw new BadCredentialsException("invalid token.....");
			}			
		}
		filterChain.doFilter(request, response);	
	}

}
