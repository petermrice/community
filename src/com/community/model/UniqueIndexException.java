/*
 * Created on Nov 11, 2004
 *
 */
package com.community.model;

/**
 * @author peter
 *
 */
public class UniqueIndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8671795887020505377L;

	/**
	 * 
	 */
	public UniqueIndexException() {
		super();
	}

	/**
	 * @param message
	 */
	public UniqueIndexException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UniqueIndexException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UniqueIndexException(String message, Throwable cause) {
		super(message, cause);
	}

}
