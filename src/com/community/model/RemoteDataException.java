/*
 * Created on Mar 31, 2005
 *
 */
package com.community.model;

/**
 * @author peter
 *
 */
public class RemoteDataException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5138484884910923860L;

	/**
	 * 
	 */
	public RemoteDataException() {
		super();

	}

	/**
	 * @param message
	 */
	public RemoteDataException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public RemoteDataException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public RemoteDataException(String message, Throwable cause) {
		super(message, cause);

	}

}
