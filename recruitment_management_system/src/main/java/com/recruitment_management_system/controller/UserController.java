package com.recruitment_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.recruitment_management_system.dto.LoginDTO;
import com.recruitment_management_system.entity.User;
import com.recruitment_management_system.service.AdminService;
import com.recruitment_management_system.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	
	@PostMapping("/admin/signup")
	ResponseEntity<?> createUser(@RequestBody User user){
		return userService.createUser(user);
	}
	
	@PostMapping("/applicant/signup")
	ResponseEntity<?> createApplicantUser(@RequestBody User user){
		return userService.createApplicantUser(user);
	}
	
	
	
	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws JsonMappingException, JsonProcessingException{
	return	userService.authenticate(loginDTO);
		
	}
	
	@GetMapping("/jobs")
	ResponseEntity<?> getAllJob(){
		return adminService.getAllJob();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
