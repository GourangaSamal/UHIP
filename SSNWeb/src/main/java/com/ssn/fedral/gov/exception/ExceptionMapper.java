package com.ssn.fedral.gov.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssn.fedral.gov.errorresponse.ErrorResponse;

@Controller
@ControllerAdvice
public class ExceptionMapper {
	@ExceptionHandler(value= {CouldNotGenerateException.class})
	
	public String CouldNotGenerateException(Model model) {
		ErrorResponse erresponse = new ErrorResponse();

		erresponse.setMessage("Invalid SsnNumber");
		model.addAttribute("erresponse", erresponse);

		return "errorpage";

	}

}
