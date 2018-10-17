package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Driver with the given id is not online ...")
public class DriverNotOnlineException extends Exception 
{

	private static final long serialVersionUID = 3031639628790959201L;

	public DriverNotOnlineException(String string) 
	{
		super(string);
	}

}
