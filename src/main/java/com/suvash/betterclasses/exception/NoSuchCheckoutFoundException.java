package com.suvash.betterclasses.exception;

import lombok.Getter;

@Getter
public class NoSuchCheckoutFoundException extends RuntimeException {
	private final String message;

	public NoSuchCheckoutFoundException(String message) {
		super(message);
		this.message = message;
	}
}
