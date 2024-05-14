package com.recruitment_management_system.controller;

import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



@RestController
@RequestMapping("/applicant")
@PreAuthorize("hasRole('Applicant')")

public class ApplicantController {

	  @PostMapping("/uploadresume")
	    public ResponseEntity<String> parseResume(@RequestParam("file") MultipartFile file) {
	        try {
	            
	            String apiUrl = "https://api.apilayer.com/resume_parser/upload";

	            // Request headers
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.set("apikey", "gNiXyflsFu3WNYCz1ZCxdWDb7oQg1Nl1");

	            // Get resume file as byte array
	            byte[] resumeBytes = file.getBytes();

	            // Create request entity
	            HttpEntity<byte[]> requestEntity = new HttpEntity<>(resumeBytes, headers);

	            // Create RestTemplate instance
	            RestTemplate restTemplate = new RestTemplate();

	            // Make POST request
	            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
	System.out.println("response"+responseEntity);
	            // Return response



	            return ResponseEntity.ok(responseEntity.getBody());
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing resume: " + e.getMessage());
	        }
	    }
}
