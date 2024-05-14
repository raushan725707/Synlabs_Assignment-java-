package com.recruitment_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_management_system.entity.Job;
import com.recruitment_management_system.service.AdminService;
import com.recruitment_management_system.service.ApplicantService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('Admin')")

public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	ApplicantService applicantService;
	
	@PostMapping("/job")
	ResponseEntity<?> createJob(@RequestBody Job job){
		return adminService.createJob(job);
	}
	@GetMapping("/job/{job_id}")
	ResponseEntity<?> jobById(@PathVariable int job_id){
		return adminService.jobById(job_id);
	}

	@GetMapping("/applicants")
	ResponseEntity<?> allApplicantsDetails(){
		
		return applicantService.fetchALLApplicant();
	}
	
	@GetMapping("/applicants/{applicant_id}")
	ResponseEntity<?> applicantById(@PathVariable int applicant_id){
		return applicantService.findApplicantById(applicant_id);
	}
	
}
