package org.cigma.ecom.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;


public class UserException extends RuntimeException {

	
	private static final long serialVersionUID = 847500838613349753L;
	
	public UserException(String message)
	{
		super(message);
	}

}
