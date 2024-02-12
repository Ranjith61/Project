package com.xworkz.jobify.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;
import com.xworkz.jobify.repo.JobifyRepositoryImpl;
import com.xworkz.jobify.util.OtpSending;
import com.xworkz.jobify.util.TLSEmail;

@Service
public class JobifyServiceImpl implements JobifyService {

	@Autowired
	private JobifyRepositoryImpl repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OtpSending otpSending;

	@Override
	public boolean ValidateAndSave(JobifyDTO dto, Model model) {
		boolean isValid = true;
		if (dto != null) {
			if (dto.getName() == null || dto.getName().isEmpty() || dto.getName().length() <= 3) {
				model.addAttribute("nameInvalid", "Name is Incorrect");
				isValid = false;
			}
			if (dto.getEmail() == null || dto.getEmail().isEmpty()
					|| !dto.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
				model.addAttribute("emailInvalid", "Please Enter Correct Email");
				isValid = false;
			}
			if (!dto.getMobile().matches("^[6-9]\\d{9}$")) {
				model.addAttribute("mobileInvalid", "Please Enter Correct Mobile no.");
				isValid = false;
			}
			if (dto.getPassword() == null || dto.getPassword().isEmpty()
					|| !dto.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$")) {
				model.addAttribute("passwordInvalid",
						"Please Enter Password having Min 8 characters, one uppercase letter, one lowercase letter, one number and one special character:");
				isValid = false;
			}
			if (dto.getConfirmPass() == null || dto.getConfirmPass().isEmpty()
					|| !dto.getConfirmPass().equals(dto.getPassword())) {
				model.addAttribute("conPasswordInvalid", "Password Does not Match");
				isValid = false;
			}

			if (isExists(dto.getEmail(), model)) {
				isValid = false;
			}

		}

		if (isValid == true) {

			String encryptedPassword = passwordEncoder.encode(dto.getPassword());
			dto.setPassword(encryptedPassword);

			JobifyEntity entity = new JobifyEntity();
			entity.setCreatedBy(dto.getEmail());
			entity.setCreatedOn(LocalDate.now());
			entity.setCount(0);
			entity.setStatus("Active");
			BeanUtils.copyProperties(dto, entity);
			TLSEmail email = new TLSEmail();
			email.newEmail(dto.getEmail(), dto.getName());
			return repo.save(entity);
		}

		return isValid;
	}

	@Override
	public boolean isExists(String email, Model model) {
		JobifyEntity found = repo.findByEmail(email);
		if (found != null) {
			if (found.getEmail().equals(email)) {
				model.addAttribute("emailExists", "Email Already Exists.");
				return true;
			}
		}
		return false;
	}

	@Override
	public List<JobifyEntity> readAll() {
		return repo.readAll();
	}

	@Override
	public JobifyEntity findByEmail(String email, Model model) {
		if (email != null & email.isEmpty()) {
			return repo.findByEmail(email);
		}
		model.addAttribute("emailNotValid", "Email is not valid");
		return null;
	}

	@Override
	public JobifyEntity login(String email, String password, Model model) {
		if (email != null && !email.isEmpty()) {
			JobifyEntity entity = repo.findByEmail(email);
			if (entity != null && entity.getStatus().equals("Active")) {
				if (entity.getCount() < 3) {
					String storedPassword = entity.getPassword();
					if (passwordEncoder.matches(password, storedPassword)) {
						return entity;
					} else {
						model.addAttribute("wrongPassword", "Enterd password is Wrong , Try Again");
						entity.setCount(entity.getCount() + 1);
						if (entity.getCount() == 3) {
							entity.setStatus("Blocked");
						}
						repo.save(entity);
						return null;
					}
				} else {
					entity.setStatus("Blocked");
					repo.save(entity);
					return null;
				}
			}
			model.addAttribute("block", "Your account has been blocked.click on forget password to change");
		}
		return null;
	}

	@Override
	public JobifyEntity forgotPassword(String email, Model model) {
		if (email != null && !email.isEmpty()) {
			JobifyEntity entity = repo.findByEmail(email);
			if (entity != null) {
				if (entity.getCount() == 3) {
					String otp = otpSending.generateOtp();
					otpSending.sendOtpToEmail(email, otp);
					entity.setOtp(otp);
					repo.save(entity);
					System.out.println("Email: " + email + " Otp : " + otp);
					System.out.println(entity);
					return entity;
				}
			}
		}
		return null;

	}

	public boolean verifyOtp(String email, String otp) {
		JobifyEntity entity = repo.findByEmail(email);
		if (entity != null) {
			// Reset the OTP after successful verification
			if (entity.getOtp().equalsIgnoreCase(otp)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public JobifyEntity resetPassword(String email, String password) {
		JobifyEntity entity = repo.findByEmail(email);
		if (entity != null) {
			String pass = passwordEncoder.encode(password);    //password from UI encrypts here
			entity.setPassword(pass);
			entity.setCount(0);
			entity.setStatus("Active");
			entity.setOtp(null);
			entity.setUpdatedBy(email);
            entity.setUpdatedOn(LocalDate.now());
            repo.save(entity);
            return entity;
		}
		return null;
	}

}