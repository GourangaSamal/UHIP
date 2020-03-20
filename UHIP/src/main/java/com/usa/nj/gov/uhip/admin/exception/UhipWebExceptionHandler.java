package com.usa.nj.gov.uhip.admin.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.usa.nj.gov.uhip.admin.constants.AppConstants;

@Controller
@ControllerAdvice

public class UhipWebExceptionHandler extends RuntimeException {
	@ExceptionHandler(value= {AdminException.class})
	public String handleAdminException(Model model) {
		return AppConstants.EXCEPTION_VIEW;
		
	}
	

}
