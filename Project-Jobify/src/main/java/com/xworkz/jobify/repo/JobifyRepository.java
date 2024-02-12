package com.xworkz.jobify.repo;

import java.util.List;

import com.xworkz.jobify.dto.JobifyEntity;

public interface JobifyRepository {
	public boolean save(JobifyEntity dto);
	
	public List<JobifyEntity> readAll();
	
	public JobifyEntity findByEmail(String email);
}
