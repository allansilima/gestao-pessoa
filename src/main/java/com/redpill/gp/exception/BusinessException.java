package com.redpill.gp.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 2445748488741443198L;

	@JsonIgnore
    private HttpStatus httpStatusCode;

    private String message;
    
    public BusinessException(HttpStatus httpStatusCode, String message) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	@Default
    private List<String> errors = List.of();
}
