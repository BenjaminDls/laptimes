package fr.benjamindanlos.laptimes.Exceptions;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
public class NotFoundException extends LaptimesException{

	public NotFoundException(@NotNull String msg) {
		super(msg);
	}

	@Override
	public HttpStatus getHttpCode(){
		return HttpStatus.NOT_FOUND;
	}
}
