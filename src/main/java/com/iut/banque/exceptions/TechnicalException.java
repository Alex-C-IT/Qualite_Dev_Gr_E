package com.iut.banque.exceptions;

/**
 * Exception utilis�e en cas de probl�me "software", quand les acc�s base de
 * donn�es ne fonctionnent pas par exemple
 */
public class TechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	public TechnicalException(String string) {
		super(string);
	}

	public TechnicalException() {
		super();
	}

	public TechnicalException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}

}
