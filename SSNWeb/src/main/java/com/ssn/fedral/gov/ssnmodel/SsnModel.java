package com.ssn.fedral.gov.ssnmodel;

import java.util.Date;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SsnModel {
	private Long ssn;
	@NotEmpty(message = "userFirstName is required")
	// @Size(min=1,max=10,message="Size should BE NOT Less Then 1 and greater then
	// 5")
	private String firstName;
	@NotNull
	/*
	 * @Size(min=1,max=11,
	 * message="Size should BE NOT Less Then 1 and greater then 11")
	 */
	@NotEmpty(message = "userLastName is required")
	private String lastName;
	private String phoneNo;

	private String dob;
	private String gender;
	private String state;
	private MultipartFile photo;

}
