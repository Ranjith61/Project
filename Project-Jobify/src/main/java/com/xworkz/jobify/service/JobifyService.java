package com.xworkz.jobify.service;

import java.util.List;

import org.springframework.ui.Model;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;

public interface JobifyService {
	
	public boolean ValidateAndSave(JobifyDTO dto, Model model);

	public List<JobifyEntity> readAll();

	boolean isExists(String email, Model model);

	public JobifyEntity findByEmail(String email, Model model);
	
	public JobifyEntity login(String email, String password, Model model);
	
	public JobifyEntity forgotPassword(String email, Model model);
		
	public JobifyEntity resetPassword(String email, String password);
	}

