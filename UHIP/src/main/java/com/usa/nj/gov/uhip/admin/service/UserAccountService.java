package com.usa.nj.gov.uhip.admin.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.usa.nj.gov.uhip.admin.model.UserAccount;

public interface UserAccountService {
	public boolean createUserAccount(UserAccount userAccount);
	public String validateEmail(String email);
	public List<UserAccount> retrieveAllAccounts();
	public String updateActiveSwitch( Integer userId);
	public UserAccount getAccById(Integer userId);

}
