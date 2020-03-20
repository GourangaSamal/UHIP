package com.ssn.fedral.gov.ssnentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "SsnRepositorry")
public class SsnMaster {
	@Id
	@GeneratedValue(generator = "ssn_seq_generator")
	@SequenceGenerator(name = "ssn_seq_generator", sequenceName = "ssn_seq", initialValue = 876891111, allocationSize = 1)
	@Column(name = "SSN_NO", length = 15)
	private Long ssn;

	@Column(name = "FIRST_NAME", length = 20)
	private String firstName;

	@Column(name = "LAST_NAME", length = 20)
	private String lastName;

	@Column(name = "GENDER", length = 7)
	private String gender;

	@Column(name = "PHONE_NO", length = 20)
	private String phoneNo;
	/* @DateTimeFormat(pattern = "dd-MM-yyyy") */
	@Column(name = "DOB", length = 15)
	private Date dob;

	@Column(name = "STATE", length = 20)
	private String state;
	@Lob
	@Column(name = "PHOTO")
	private byte[] photo;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", length = 15)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", length = 15)
	private Date updatedDate;

	@Column(name = "CREATED_BY", length = 20)
	private String createdBy;

	@Column(name = "UPDATE_BY", length = 20)
	private String updatedBy;

}
