package com.ssn.fedral.gov.ssnservice;

import java.util.List;

import org.springframework.ui.Model;

import com.ssn.fedral.gov.ssnentity.SsnMaster;
import com.ssn.fedral.gov.ssnmodel.SsnModel;
import com.ssn.fedral.gov.stateentity.UsaState;

public interface Ssnintrface {
	public Long SsnEnrolment(SsnModel model);
	public List<String> rettriveAllstate();
	public List<SsnModel> getAllssn(); 
	public SsnModel findStateName(Long ssn);
}