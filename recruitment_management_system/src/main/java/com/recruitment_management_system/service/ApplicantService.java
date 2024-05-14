package com.recruitment_management_system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.recruitment_management_system.entity.Job;
import com.recruitment_management_system.entity.User;
import com.recruitment_management_system.repository.UserRepository;

@Service
public class ApplicantService {

	@Autowired
	UserRepository repository;
	

public ResponseEntity<?> findApplicantById(int id) {
	try {

	User user=	repository.findById(id).orElseThrow(NoSuchElementException::new);
Map<Object, Object> m=new HashMap<>();
m.put("status", 200);
m.put("response", user);
return new ResponseEntity<>(m,HttpStatus.OK);
}
	catch(NoSuchElementException e) {
		  Map<Object, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", 404);
	        errorResponse.put("message", "Applicant with ID " + id + " not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	
}





















public ResponseEntity<?> fetchALLApplicant() {
	List<User> user=	repository.findAll();
	Map<Object, Object> m=new HashMap<>();
	m.put("status", 200);
	m.put("response", user);
	return new ResponseEntity<>(m,HttpStatus.OK);
}
	
}
