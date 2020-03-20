package com.usa.nj.gov.uhip.admin.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usa.nj.gov.uhip.admin.entity.UserAccountEntity;

import lombok.Data;
/**
 * This class contain T=entity class with id =serializable ( support all value i.e primary key )
 * @author Dell User
 *
 */
@Repository

public interface UserAccountRepository  extends JpaRepository<UserAccountEntity, Serializable>{
//write custom method with path: emailid match with jsp createUsrAcc 
	@Query(value="select count(emailId)from UserAccountEntity where emailId=:email")
	public Integer findBYEmail(String email);
	
	@Query(value="update userAccountEntity set activeSwitch=:activeSwitch where userId=:userId")
	public Integer updateActiveSwitch(String activeSwitch,Integer userId);
	
	@Query(value="select activeSwitch from UserAccountEntity where userId=:userId")
	public String getActiveSwitch(Integer userId);
	
	//login 
	@Query(value="select email from UserAccountEntity where email=:email")
	public UserAccountEntity findByEmailForLogin(String email);
	
}
