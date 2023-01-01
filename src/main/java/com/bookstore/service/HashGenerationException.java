package com.bookstore.service;

public class HashGenerationException extends RuntimeException {

	public HashGenerationException(String message) {
		super(message);
	}
	
	public HashGenerationException(String message,Throwable cause) {
		super(message,cause);
	}
}
