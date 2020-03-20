package com.ssn.fedral.gov.ssnservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssn.fedral.gov.ssnRepository.SsnRepositorry;
import com.ssn.fedral.gov.ssnentity.SsnMaster;
import com.ssn.fedral.gov.ssnexception.InvalidSsnException;
import com.ssn.fedral.gov.ssnmodel.SsnModel;
import com.ssn.fedral.gov.ssnrestcontroller.SsnRestController;
import com.ssn.fedral.gov.stateentity.UsaState;
import com.ssn.fedral.gov.usastate.UsastatesRepository;

@Service
public class Ssnimpliment implements Ssnintrface {
	@Autowired
	private SsnRepositorry repository;
	@Autowired
	private UsastatesRepository staterepository;
	private static final Logger logger = LoggerFactory.getLogger(Ssnimpliment.class);
	
	/**
	 * Method SsnEnrolment
	 * @param SsnModel
	 * @return Ssn
	 */
	@Override
	public Long SsnEnrolment(SsnModel model) {
		logger.info("**Running The SsnEnrolment**");

		// TODO Auto-generated method stub
		SsnMaster ssnmaster = new SsnMaster();
		SimpleDateFormat dateFrmt = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {

			date = dateFrmt.parse(model.getDob());
			logger.trace("**date**" + date);

		} catch (ParseException e) {
			logger.error("**Excption Catch in DateConversion**" + e);

			e.printStackTrace();
		}
		logger.info("Before Copy model to master");
		BeanUtils.copyProperties(model, ssnmaster);
		// Use Dao class
		ssnmaster.setDob(date);
		logger.debug("**Befor Calling RepositoryMethod**");
		ssnmaster = repository.save(ssnmaster);
		logger.debug("**After Getting ssnmaster **" + ssnmaster);
		return ssnmaster.getSsn();
	}
	/**
	 * Method rettriveAllstate
	 * @param 
	 * @returnusaStatelist
	 */
	@Override
	public List<String> rettriveAllstate() {
		// TODO Auto-generated method stub
		logger.info("**rettriveAllstate() Execution Started**");
		List<UsaState> usastate = staterepository.findAll();
		logger.debug("**After Getting All usastate**" + usastate);
		List<String> usaStatelist = new ArrayList<String>();
		if (!usastate.isEmpty()) {
			logger.warn("**Checking Condition !usastate.isEmpty())**");
			for (UsaState usastateEntity : usastate) {
				logger.debug("UsaState usastateEntity : usastate ");
				usaStatelist.add(usastateEntity.getSTATENAME());
				logger.trace("**Running The For Loop For Getting StateName**");
			}

		}
		return usaStatelist;

	}

	/**
	 * Method  getAllssn
	 * @param 
	 * @return model
	 */
	@Override
	public List<SsnModel> getAllssn() {
		// TODO Auto-generated method stub

		logger.info("**getAllssn() Execution Started**");
		List<SsnMaster> master = repository.findAll();
		logger.debug("**Execution Complited and got master From Database**");
		List<SsnModel> model = new ArrayList<SsnModel>();
		if (!master.isEmpty()) {
			logger.warn("**isEmpty()**");
			for (SsnMaster ssn : master) {
				logger.debug("**Loop Execution is continuing **");
				SsnModel mod = new SsnModel();
				BeanUtils.copyProperties(ssn, mod);
				model.add(mod);
				logger.trace("**getAllssn() Method Execution **");
			}
		}
		logger.info("getAllssn() Execution Complited");
		return model;
	}
	/**
	 * Method findStateName
	 *
	 * @param Long ssn
	 * @return ssnmodel
	 */
	@Override
	public SsnModel findStateName(Long ssn) {
		logger.info("findStateName Method  Exeution Started");
		// TODO Auto-generated method stub
		// local variable
		SsnModel ssnmodel = null;
		// Call Repository Class Method

		Optional<SsnMaster> ssnmaster = repository.findById(ssn);
		if (ssnmaster.isPresent()) {
			logger.warn("**ssnmaster.isPresent()**");
			ssnmodel = new SsnModel();
			SsnMaster master = ssnmaster.get();
			BeanUtils.copyProperties(master, ssnmodel);
		} else {
			logger.debug("**InvalidSsnException(\"Wrong Ssn Number\")**");
			throw new InvalidSsnException("Wrong Ssn Number");

		}
		logger.info("findStateName method Execution Complited");
		return ssnmodel;

	}

}
