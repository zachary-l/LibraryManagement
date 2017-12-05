package com.library.exception;

import org.evergreen.web.exception.ActionException;

public class FlowException extends ActionException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlowException(String exception, int responseStatus) {
		super(exception, responseStatus);
		// TODO Auto-generated constructor stub
	}

}
