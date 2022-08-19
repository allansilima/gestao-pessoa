package com.redpill.gp.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.redpill.gp.exception.BusinessException;
import com.redpill.gp.exception.ErrorCustomException;

@ControllerAdvice
public class ControlExceptionHandler {

	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<Object> handleConflict(BusinessException ex) {
		var errorCustom = ErrorCustomException.builder()
				.errors(List.of(ex.getMessage()))
				.build();

		return ResponseEntity.status(ex.getHttpStatusCode()).body(errorCustom);
	}

	@ExceptionHandler(value = { NullPointerException.class })
	protected ResponseEntity<Object> handleConflict(NullPointerException ex) {
		var errorCustom = ErrorCustomException.builder()
				.errors(List.of(ex.getMessage()))
				.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorCustom);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validationError(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();

		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).map(String::new)
				.collect(Collectors.toList());

		var errorCustom = ErrorCustomException.builder()
				.errors(errors)
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCustom);
	}
}
