package com.recruitment_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_management_system.service.JobService;

@RestController
@RequestMapping("/jobs")
@PreAuthorize("hasRole('Applicant')")

public class JobController {

	@Autowired
	JobService jobService;
	@GetMapping("/apply")
	ResponseEntity<?> jobByid(@RequestParam int job_id){
		return jobService.jobById(job_id);
	}
	
}
