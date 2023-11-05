package com.iut.banque.exceptions;

/**
 * Exception utilis�e quand un param�tre ne correspond pas au format attendu
 * (par exemple, entrer des lettres pour faire un d�bit)
 */
public class IllegalFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalFormatException() {
		super();
	}

	public IllegalFormatException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IllegalFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalFormatException(String arg0) {
		super(arg0);
	}

	public IllegalFormatException(Throwable arg0) {
		super(arg0);
	}

}
