package com.usa.nj.gov.uhip.admin.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class UserAccount {
	private String userAccId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	@DateTimeFormat(pattern="dd/M/yyyy")
	private Date dob;
	private String gender;
	private String role;
	private Long ssn;
	private String activeSw;
	private Date createdDate;
	private Date updatedDate;
	private Date createdBy;
	private Date updatedBy;
	

}
