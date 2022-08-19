package com.redpill.gp.exception;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ErrorCustomException implements Serializable {

	private static final long serialVersionUID = -7129191518061217818L;

	@Default
    private List<String> errors = List.of();
}
