package com.usa.nj.gov.uhip.admin.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.hibernate.validator.internal.util.privilegedactions.GetInstancesFromServiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.nj.gov.uhip.admin.constants.AppConstants;
import com.usa.nj.gov.uhip.admin.entity.UserAccountEntity;
import com.usa.nj.gov.uhip.admin.exception.AdminException;
import com.usa.nj.gov.uhip.admin.model.UserAccount;
import com.usa.nj.gov.uhip.admin.properties.AppProperties;
import com.usa.nj.gov.uhip.admin.repository.UserAccountRepository;
/**
 * service class take model to talk wit repository class
 * @author Dell User
 *
 */
import com.usa.nj.gov.uhip.util.MailService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserAccountRepository userAccRepo;
	@Autowired
	private MailService mailService;
	@Autowired
	private AppProperties props;

	// create logger obj
	private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Override
	public boolean createUserAccount(UserAccount userAccount) {
		logger.debug("**createUserAccount() started **");
		boolean isSaved = false;
		try {
			UserAccountEntity entity = new UserAccountEntity();
			BeanUtils.copyProperties(userAccount, entity);
			// setting active switch for new user account
			entity.setActiveSw(AppConstants.ACTIVE_SW);
			// calling persistency layer method where first store the data in db
			entity = userAccRepo.save(entity);
			// here after storing data in db ,we retrive AccId then condition check
			if (entity.getUserAccId() > 0) {

				// calling sendAccCreationEmail() for mail sending
				sendAccCreationEmail(userAccount);
				isSaved = true;
			}
		} catch (Exception e) {
			logger.error("**Exception Occured in createdUserAcc **");
			throw new AdminException(e.getMessage());

		}
		logger.debug("**createUserAccount() ended**");

		return isSaved;

	}

	@Override
	public String validateEmail(String email) {
		logger.debug("**validateEmail() started **");

		Integer cnt = userAccRepo.findBYEmail(email);
		/*
		 * if(cnt>=1) { return"Duplicate"; } return "Unique"; }
		 */
		if (cnt >= 1) {
			logger.warn("**Duplicate email found **");
		}
		logger.debug("** validateEmail() ended **");
		logger.info("** validateEmail() completed successfully");
		return (cnt >= 1) ? AppConstants.DUPLICATE : AppConstants.UNIQUE;

	}
	/**
	 * This method use for password encryption
	 * @param password
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	private String encryptPass(String password) throws Exception, Exception {
		Cipher aesCipher=Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, getSercretEncryptionKey());
		byte[] byteCipherText=aesCipher.doFinal(password.getBytes());
		System.out.println(new String(byteCipherText));
		Encoder encoder=Base64.getEncoder();
		String encryptedPass=encoder.encodeToString(byteCipherText);
		
		return encryptedPass;
	}
	
	private SecretKey getSercretEncryptionKey()throws Exception
	{
		KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		//generate key
		SecretKey key=keyGenerator.generateKey();
		
		return key;
	}

	/**
	 * This method use for mail sending.
	 * 
	 * @param to
	 * @throws IOException
	 */

	public void sendAccCreationEmail(UserAccount accModel) throws IOException {
		// logic to send mail
		// getting subject from AppProperties
		String subject = props.getUhipprops().get(AppConstants.ACC_CREATION_EMAIL_SUB);
		// logic to email body
		String body = getMailBody(accModel);

		mailService.sendAccRegEmail(accModel.getEmailId(), subject, body);
	}

	/**
	 * this method is used to read mail body content from a file and format this
	 * method used for body sending of email
	 * 
	 * @return
	 * @throws IOException
	 */
	private String getMailBody(UserAccount accModel) throws IOException {
		logger.debug("getMailBody() method started");
		String bodyContent = null;
		String fileName = "Acc_Creation_Email_Body_Template.txt";
		StringBuffer sb = new StringBuffer("");
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// loading file
			file = new File(fileName);
			// read by character by character
			fr = new FileReader(file);
			// read line by line
			br = new BufferedReader(fr);
			logger.debug("file loaded successfully");
			// reading one line from file
			String line = br.readLine();
			// condition to replace placeholder
			while (line != null) {
				logger.debug("enter into while loop");
				// process line
				// if the line is there and $ is not then
				// this will execute to skip that line
				if (line.equals("") || !line.contains("$")) {
					sb.append(line);
					// read next line
					line = br.readLine();
					// skip this line
					continue;
				}

				if (line.contains("${FNAME}")) {
					line=line.replace("${FNAME}", accModel.getFirstName());
					sb.append(line);
				}
				if (line.contains("${LNAME}")) {
					line=line.replace("${LNAME}", accModel.getLastName());
					sb.append(line);
				}
				if (line.contains("${URL}")) {
					line=line.replace("${URL}", "url");
					sb.append(line);
				}

				if (line.contains("${EMAIL}")) {
					line=line.replace("${EMAIL}", accModel.getEmailId());
					sb.append(line);
				}

				if (line.contains("${PWD}")) {
					line=line.replace("${PWD}", accModel.getPassword());
					sb.append(line);
				}
				if (line.contains("${PHNO}")) {
					String phno = props.getUhipprops().get(AppConstants.UHIP_ADMIN_PHNO);
					line = line.replace("${PHNO}", phno);
					sb.append(line);
				}
				logger.debug("");
				// read next line from file
				line = br.readLine();
			}
			logger.debug("Exited from while loop");
		} catch (Exception e) {
			logger.error("Exception occured while reading Email Body::" + e.getMessage());
			e.printStackTrace();
		} /*
			 * finally {
			 * 
			 * if(br!=null)
			 * 
			 * br.close(); if(fr!=null) fr.close();
			 * 
			 * }
			 */
		logger.debug("getmailbody() method ended");
		logger.info("getmailbody() method successfully completed");
		return sb.toString();

	}

	@Override
	public List<UserAccount> retrieveAllAccounts() {
		List<UserAccount> listModel=null;
		List<UserAccountEntity> listEntity=null;
		listModel=new ArrayList<UserAccount>();
		listEntity=userAccRepo.findAll();
		if(!listEntity.isEmpty()) {
			for(UserAccountEntity entity:listEntity) {
				UserAccount model=new UserAccount();
				BeanUtils.copyProperties(entity, model);
				model.setUserAccId(entity.getUserAccId().toString());
				listModel.add(model);
				
				
			}
		}
		
		return listModel;
	}

	@Override
	public String updateActiveSwitch(Integer userId) {
		String accStatus=null;
		String activeSwitch=userAccRepo.getActiveSwitch(userId);
		if(activeSwitch.equals("Y")) {
			activeSwitch="N";
			accStatus="Deactivated";
			
		}
		else if(activeSwitch.equals("N")){
			
			activeSwitch="Y";
			accStatus="Deactivated";
			
		}
		Integer status=userAccRepo.updateActiveSwitch(activeSwitch, userId);
		return accStatus;
	}

	@Override
	public UserAccount getAccById(Integer userId) {
		UserAccount userAccountModel= new UserAccount();
		UserAccountEntity userAccountEntity=null;
		 Optional<UserAccountEntity> optionalEntity =userAccRepo.findById(userId);
		 if(optionalEntity .isPresent()) {
			userAccountEntity =optionalEntity.get();
			BeanUtils.copyProperties(userAccountEntity, userAccountModel);
		 }
		return userAccountModel;
	}
	
	
}


