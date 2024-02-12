package com.xworkz.jobify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;
import com.xworkz.jobify.service.JobifyServiceImpl;

@Controller
public class JobifyController {
	@Autowired
	private JobifyServiceImpl service;

	public JobifyController() {
		System.out.println("Jobify controller created");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(JobifyDTO dto, Model model) {
		
		boolean saved = service.ValidateAndSave(dto, model);
		if (saved) {
			model.addAttribute("saved", "Registered Successfully");
			return "Register";
		}
		return "Register";
	}
	
	 @RequestMapping(value="/login", method = RequestMethod.POST)
	 public String login(@RequestParam String email, @RequestParam String password, Model model) {  
    	JobifyEntity entity = service.login(email, password, model) ;
    	if(entity!=null) {
    		if(entity.getAccount().equals("Job Seeker")) {
    			return "JobSeeker";
    		}
    		return "JobProvider";
    		}
		return "Login";
    	}
     
//	 @RequestMapping(value = "/forgot",method = RequestMethod.POST)
//	 public String forgot(@RequestParam String email, String otp,Model model) {
//		 
//		JobifyEntity entity= service.forgotPassword(email, model);
//		if(entity!=null) {
//			System.out.println(email);
//			model.addAttribute("otp", "Otp Sent Successfully...");
//			model.addAttribute("email", email);
//		}else {
//			model.addAttribute("email", email);
//			model.addAttribute("noOtp", "Otp Not Sent..");			
//		}
//		
//		boolean isOtpValid = service.verifyOtp(email, otp);
//	    if (isOtpValid) {
//	        model.addAttribute("email", email);
//	        // Redirect to a password reset page or any other desired page upon successful OTP verification
//	        return "ResetPassword";
//	    } else {
//	        model.addAttribute("email", email);
//	        model.addAttribute("otpVerificationError", "Invalid OTP");
//	        return "ForgotPassword";
//	    }
//		 
//	 }

	 
	 
	 
	 
	 @RequestMapping(value = "/forgot", method = RequestMethod.POST)
	 public String forgot(@RequestParam String email, @RequestParam(required = false) String otp, Model model) {
	     // Send OTP if OTP is not provided
	     if (otp == null || otp.isEmpty()) {
	         JobifyEntity entity = service.forgotPassword(email, model);
	         if (entity != null) {
	             System.out.println(email);
	             model.addAttribute("otpSent", "Otp sent successfully..");
	             model.addAttribute("email", email);
	             return "ForgotPassword";
	         } else {
	             model.addAttribute("email", email);
	             model.addAttribute("noOtp", "Otp Not Sent..");
	             return "ForgotPassword";
	         }
	     }
	     
	     // Verify OTP if OTP is provided
	     boolean isOtpValid = service.verifyOtp(email, otp);
	     if (isOtpValid) {
	         model.addAttribute("email", email);
	         // Redirect to a password reset page or any other desired page upon successful OTP verification
	         return "ResetPassword";
	     } else {
	         model.addAttribute("email", email);
	         model.addAttribute("otpVerificationError", "Invalid OTP");
	         return "ForgotPassword";
	     }
	 }

	 @RequestMapping(value="/reset", method=RequestMethod.POST)
	 public String reset(@RequestParam String email,@RequestParam String password,Model model) {
		 JobifyEntity entity = service.resetPassword(email, password);
		 if(entity!=null) {
			model.addAttribute("password", "NewPassword set Successfully...");
			return "Login";
		 }else {
			 model.addAttribute("wrongpassword", "NewPassword has not set Successfully...");
			 return "ResetPassword";
		 }
		 
	 }
     
}
           
