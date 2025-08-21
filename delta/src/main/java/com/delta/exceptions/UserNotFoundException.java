package com.delta.exceptions;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
