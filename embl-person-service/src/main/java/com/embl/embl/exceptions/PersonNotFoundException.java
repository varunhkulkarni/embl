package com.embl.embl.exceptions;

/**
 * Custom exception for the Person Controller
 * 
 * @author Varun-Kulkarni
 *
 */
public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String exception) {
		super(exception);
	}

	public PersonNotFoundException(long id) {
		super("Person id not found : " + id);
	}

}
