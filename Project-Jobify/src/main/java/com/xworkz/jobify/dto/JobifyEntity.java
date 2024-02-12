package com.xworkz.jobify.dto;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="Register")
@NamedQuery(name = "findAllJobifyEntities", query = "SELECT dto FROM JobifyEntity dto")
@NamedQuery(name = "findEmail", query="Select dto from JobifyEntity dto where dto.email=:em")
public class JobifyEntity {//Back end
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String mobile;
	private String password;
	private String account;
	private String createdBy;
	private LocalDate createdOn;
	private String updatedBy;
	private LocalDate updatedOn;
	private int count;
	private String status;
	private String otp;
}