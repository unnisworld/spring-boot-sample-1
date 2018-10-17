package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Car with the given id is not found ...")
public class CarNotFoundException extends Exception 
{
	private static final long serialVersionUID = -8835236300970163410L;

	public CarNotFoundException(String string) 
	{
		super(string);
	}

}
