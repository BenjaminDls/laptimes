package fr.benjamindanlos.laptimes.ExceptionHandler;

import fr.benjamindanlos.laptimes.DTO.ErrorDTO;
import fr.benjamindanlos.laptimes.Exceptions.LaptimesException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
	@ExceptionHandler(value = LaptimesException.class)
	public ResponseEntity<ErrorDTO> handleExceptions(LaptimesException e) {
		ErrorDTO dto = new ErrorDTO(e.getMessage(), e.getHttpCode().value());
		return ResponseEntity.status(e.getHttpCode().value()).body(dto);
	}
}
