package com.recruitment_management_system.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.recruitment_management_system.configuration.JwtService;
import com.recruitment_management_system.dto.LoginDTO;
import com.recruitment_management_system.entity.User;
import com.recruitment_management_system.pnum.UserType;
import com.recruitment_management_system.repository.UserRepository;

@Service
public class UserService {
@Autowired
UserRepository userRepository;
@Autowired
PasswordEncoder passwordEncoder;
@Autowired
JwtService jwtService;

@Autowired
AuthenticationManager authenticationManager;

public ResponseEntity<?> createUser(User user){
	
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	user.setUserType(UserType.Admin);
	userRepository.save(user);
	var token=jwtService.generateToken(user);
	
	
	
User userr=	userRepository.save(user);
	Map<Object, Object> m=new HashMap<>();
	m.put("status", 201);
	m.put("response", userr);
	m.put("token", token);
	m.put("msg", "user is created");
	
return new ResponseEntity<>(m,HttpStatus.CREATED);
}


public ResponseEntity<?> createApplicantUser(User user) {
	// TODO Auto-generated method stub
	
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	user.setUserType(UserType.Applicant);
	userRepository.save(user);
	var token=jwtService.generateToken(user);
	
	
	
User userr=	userRepository.save(user);
	Map<Object, Object> m=new HashMap<>();
	m.put("status", 201);
	m.put("response", userr);
	m.put("token", token);
	m.put("msg", "user is created");
	
	return new ResponseEntity<>(m,HttpStatus.CREATED);
}





public ResponseEntity<?> authenticate(LoginDTO request) throws JsonMappingException, JsonProcessingException {
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

	
	
	var user= userRepository.findByUsername(request.getUsername()).orElseThrow();
	System.out.println(user+"98998");
	
	
	var token=jwtService.generateToken(user);



Map<Object, Object> m=new HashMap<>();
	m.put("status", 200);
	m.put("token", token);
	m.put("user", user);
	
	
	return new ResponseEntity<>(m, HttpStatus.OK);
}

























	
}
