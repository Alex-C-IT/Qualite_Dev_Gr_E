package com.iut.banque.exceptions;

public class IllegalOperationException extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalOperationException() {
		super();
	}

	public IllegalOperationException(String message) {
		super(message);
	}

	public IllegalOperationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalOperationException(Throwable cause) {
		super(cause);
	}
}
