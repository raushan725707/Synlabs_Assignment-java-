package com.recruitment_management_system.service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.recruitment_management_system.entity.Job;
import com.recruitment_management_system.repository.JobRepository;

@Service
public class JobService {

	
	@Autowired
	JobRepository jobRepository;
	
	public ResponseEntity<?> jobById(int id) {
	    try {
	        Job job = jobRepository.findById(id).orElseThrow(NoSuchElementException::new);
job.setTotalApplications(job.getTotalApplications()+1);
jobRepository.save(job);
	        Map<Object, Object> response = new HashMap<>();
	        response.put("status", 302);
	        response.put("response", job);
	        return new ResponseEntity<>(response, HttpStatus.FOUND);
	    } catch (NoSuchElementException e) {
	        // Handle the case where the job with the given ID is not found
	        Map<Object, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", 404);
	        errorResponse.put("message", "Job with ID " + id + " not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	}
}
