package com.ssn.fedral.gov.ssnexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.ssn.fedral.gov.errorresponse.ErrorResponse;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(value = InvalidSsnException.class)
	public ResponseEntity<ErrorResponse> InvalidSsnException() {
		ErrorResponse errorresponse = new ErrorResponse();
		errorresponse.setErrorCode(HttpStatus.BAD_REQUEST);
		errorresponse.setMessage("Invalid SsnNumber");
		return new ResponseEntity<ErrorResponse>(errorresponse, HttpStatus.BAD_REQUEST);
	}
}
