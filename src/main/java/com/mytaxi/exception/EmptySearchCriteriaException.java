package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Search criteria can not be empty ...")
public class EmptySearchCriteriaException extends Exception 
{
	private static final long serialVersionUID = 5626885029254307808L;

}
