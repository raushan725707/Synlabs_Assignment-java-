package com.recruitment_management_system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Job {

	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String  description;
	private LocalDateTime postedOn;
	private int totalApplications;
	private String Company;
	private String  PostedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(LocalDateTime postedOn) {
		this.postedOn = postedOn;
	}
	public int getTotalApplications() {
		return totalApplications;
	}
	public void setTotalApplications(int totalApplications) {
		this.totalApplications = totalApplications;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getPostedBy() {
		return PostedBy;
	}
	public void setPostedBy(String postedBy) {
		PostedBy = postedBy;
	}
	
	
	
}
