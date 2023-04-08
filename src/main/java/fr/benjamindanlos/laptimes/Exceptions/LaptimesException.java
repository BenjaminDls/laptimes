package fr.benjamindanlos.laptimes.Exceptions;

import org.springframework.http.HttpStatus;

abstract public class LaptimesException extends RuntimeException{
	abstract public HttpStatus getHttpCode();

	public LaptimesException(String message) {
		super(message);
	}
}
