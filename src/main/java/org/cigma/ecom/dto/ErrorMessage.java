package org.cigma.ecom.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
	
	private Date timestamp;
	private String message;

	public ErrorMessage(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	

}
