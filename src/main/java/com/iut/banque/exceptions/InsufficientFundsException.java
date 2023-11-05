package com.iut.banque.exceptions;

/**
 * Exception utilis�e pour signaler quand la transaction banquaire qu'on
 * souhaite r�aliser ne peut se faire en raison de fonds insuffisant (un d�bit
 * d�passant le d�couvert autoris� par exemple)
 */
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientFundsException() {
		super();
	}

	public InsufficientFundsException(String message) {
		super(message);
	}

	public InsufficientFundsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InsufficientFundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientFundsException(Throwable cause) {
		super(cause);
	}

}
