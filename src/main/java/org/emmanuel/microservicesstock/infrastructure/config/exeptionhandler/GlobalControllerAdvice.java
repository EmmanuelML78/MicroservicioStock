package org.emmanuel.microservicesstock.infrastructure.config.exeptionhandler;

import org.emmanuel.microservicesstock.domain.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CategoryNotFoundException.class)
	public ErrorResponse handleCategoryNotFoundException(CategoryNotFoundException ex) {
		return ErrorResponse.builder()
				.code("CATEGORY_NOT_FOUND")
				.message(ex.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();

		return ErrorResponse.builder()
				.code("INVALID_REQUEST")
				.message("Invalid request")
				.details(result.getFieldErrors()
						.stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage)
						.toList())
				.timestamp(LocalDateTime.now())
				.build();
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception exception) {
		return ErrorResponse.builder()
				.code("INTERNAL_SERVER_ERROR")
				.message("An unexpected error occurred")
				.details(Collections.singletonList(exception.getMessage()))
				.timestamp(LocalDateTime.now())
				.build();
	}
}
