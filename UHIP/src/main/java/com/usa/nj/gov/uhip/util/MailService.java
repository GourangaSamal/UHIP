package com.usa.nj.gov.uhip.util;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Service
public class MailService {
	private static final Logger logger=LoggerFactory.getLogger(MailService.class);
	//boot provide predefind class for mail sending.
	@Autowired
	private JavaMailSender mailSender;
	/**
	 * This method for sending account regitration email.
	 * @param to
	 * @param subject
	 * @param body
	 * @return
	 */
	public boolean sendAccRegEmail(String to,String subject,String body) {
		logger.debug(" ** sending Account Registration Email Start **"  );
		//it is  used for  sending plain text msg
		/*SimpleMailMessage mailMsg=new SimpleMailMessage();
		try{
		mailMsg.setSubject(subject);
		mailMsg.setText(body);
		mailMsg.setTo(to);
		mailMsg.setSentDate(new Date());*/
		try
		{
			MimeMessage msg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(msg,true);
			helper.setTo(to);
			helper.setSubject(subject);

			helper.setText(body, true);
			helper.setSentDate(new Date());
			mailSender.send(msg);
		}catch(Exception e) {
			logger.error("Exception occured in java Api::"+e.getMessage());
		}

		logger.debug(" ** sending Account Registration Email ended **"  );
		logger.info(" ** sending Account Registration Email Completed **"  );
		return true;

	}



}
