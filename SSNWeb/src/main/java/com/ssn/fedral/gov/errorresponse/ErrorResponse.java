package com.ssn.fedral.gov.errorresponse;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
	private HttpStatus errorCode;
	private String message;

}
