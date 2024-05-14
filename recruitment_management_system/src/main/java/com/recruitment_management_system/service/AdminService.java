package com.recruitment_management_system.service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.recruitment_management_system.entity.Job;
import com.recruitment_management_system.repository.JobRepository;

@Service
public class AdminService {

	@Autowired
	JobRepository jobRepository;
	
	public ResponseEntity<?> createJob(Job job) {
	    try {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String username = authentication.getName();
	        
	        if (!userHasPermissionToCreateJob(authentication)) {
	            throw new AccessDeniedException("You don't have permission to create a job.");
	        }

	        job.setPostedOn(LocalDateTime.now());
	        job.setPostedBy(username);
	        Job savedJob = jobRepository.save(job);
	        
	        Map<Object, Object> response = new HashMap<>();
	        response.put("status", 201);
	        response.put("response", savedJob);
	        response.put("msg", "Job created");

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    } catch (AccessDeniedException e) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}
	private boolean userHasPermissionToCreateJob(Authentication authentication) {
		boolean isAdmin = authentication.getAuthorities().stream()
	            .anyMatch(authority -> authority.getAuthority().equals("Admin"));
	    
	    System.out.println("User has Admin role: " + isAdmin);
		
		
		
		return authentication.getAuthorities().stream()
	            .anyMatch(authority -> authority.getAuthority().equals("Admin"));
	    
	}
	public ResponseEntity<?> getAllJob() {
		
	List<Job> job=	jobRepository.findAll();
		Map<Object,Object> m=new HashMap<>();
		m.put("status", 200);
		m.put("response", job);
	
		return new ResponseEntity<>(m,HttpStatus.OK);
	}
	public ResponseEntity<?> jobById(int id) {
	    try {
	        Job job = jobRepository.findById(id).orElseThrow(NoSuchElementException::new);

	        Map<Object, Object> response = new HashMap<>();
	        response.put("status", 302);
	        response.put("response", job);
	        return new ResponseEntity<>(response, HttpStatus.FOUND);
	    } catch (NoSuchElementException e) {
	        Map<Object, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", 404);
	        errorResponse.put("message", "Job with ID " + id + " not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	}
}
