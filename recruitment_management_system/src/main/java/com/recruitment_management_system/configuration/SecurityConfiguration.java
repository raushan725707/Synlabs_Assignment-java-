package com.recruitment_management_system.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	
	@Autowired
	private JwtAuthenticationFilter jwtAUthFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
	.requestMatchers("/admin/**").hasAuthority("Admin")
	.requestMatchers("/applicant/**","/jobs/**").hasAnyAuthority("Applicant")
		.requestMatchers("/user/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAUthFilter,UsernamePasswordAuthenticationFilter.class)
		;
		
		
		
		
		return http.build();
	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//	  CorsConfiguration configuration = new CorsConfiguration();
//	  configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//	  configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
//	  configuration.setAllowCredentials(true);
//	  configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type","Accept", "Accept-Encoding", "Accept-Language",
//              "Cache-Control", "Connection", "Content-Type", "Cookie", "Host",
//              "Origin", "Referer", "User-Agent"));
//	  configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
//	  configuration.setMaxAge(3600L);
//	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	  source.registerCorsConfiguration("/**", configuration);
//
//	  return source;
//	}
//	
	
	
	
//	 @Bean
//	    public CorsFilter corsFilter() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        CorsConfiguration config = new CorsConfiguration();
//	        config.addAllowedOrigin("*"); // Allow all origins
//	        config.addAllowedMethod("*"); // Allow all methods
//	        config.addAllowedHeader("*"); // Allow all headers
//	        config.setAllowCredentials(true); // Allow sending cookies
//	        source.registerCorsConfiguration("/**", config);
//	        return new CorsFilter(source);
//	    }
	
	
	
	
	
	
	
}
