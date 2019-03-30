/*
 * Created on Nov 10, 2004
 *
 */
package com.community.model;

/**
 * @author peter
 *
 */
public class DatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2105969513830943729L;

	/**
	 * 
	 */
	public DatabaseException() {
		super();
	}

	/**
	 * @param message
	 */
	public DatabaseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DatabaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
