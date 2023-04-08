package fr.benjamindanlos.laptimes.Exceptions;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
public class BadRequestException extends LaptimesException{

	public BadRequestException(@NotNull String msg) {
		super(msg);
	}

	@Override
	public HttpStatus getHttpCode() {
		return HttpStatus.BAD_REQUEST;
	}
}
