package com.recruitment_management_system.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	
	
	
	@Autowired
	
	private  JwtService jwtService;
	
	@Autowired
	private  UserDetailsService userDetailsService ;
	
	
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String email;
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return ;
		}
		
		jwt=authHeader.substring(7);
		
		email=jwtService.extractUserName(jwt);
		
		if(email !=null &&  SecurityContextHolder.getContext().getAuthentication() ==null) {
			UserDetails  userdetails=this.userDetailsService.loadUserByUsername(email);
			
			if(jwtService.isTokenValid(jwt, userdetails)) {
				
				UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
		
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			
			
			
			}
			
		}
		
		
		
	filterChain.doFilter(request, response);	
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
