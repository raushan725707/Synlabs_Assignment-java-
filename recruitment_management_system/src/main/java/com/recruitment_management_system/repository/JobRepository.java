package com.recruitment_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_management_system.entity.Job;

public interface JobRepository  extends JpaRepository<Job, Integer>{

}
