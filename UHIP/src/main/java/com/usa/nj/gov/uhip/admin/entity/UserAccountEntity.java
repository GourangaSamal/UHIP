package com.usa.nj.gov.uhip.admin.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "user_accounts")
@Data
public class UserAccountEntity {
	@Id
	// create custom generator but not use hibernate default generator.
	@SequenceGenerator(sequenceName = "user_acc", initialValue = 976891111, allocationSize = 1, name = "user_acc_id_seq_generator")
	// generated primary key by jpa
	@GeneratedValue(generator = "user_acc_id_seq_generator")
	private Integer userAccId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_email")
	private String emailId;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_dob")
	private Date dob;													
	@Column(name = "user_gender")
	private String gender;
	@Column(name = "user_role")
	private String role;
	@Column(name = "user_ssn")
	private Long ssn;
	@Column(name = "active_sw")
	private String activeSw;
	@CreationTimestamp
	@Column(name = "created_date", length = 15)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "updated_date", length = 15)
	private Date updatedDate;
	@Column(name = "created_by")
	private Date createdBy;
	@Column(name = "updated_by")
	private Date updatedBy;

}
