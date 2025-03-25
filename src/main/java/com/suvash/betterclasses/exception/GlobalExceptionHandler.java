package com.suvash.betterclasses.exception;

import com.suvash.betterclasses.common.CommonErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoSuchCheckoutFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<CommonErrorResponse> noSuchCheckoutFoundHandlerFoundHandler(NoSuchCheckoutFoundException ex) {
		CommonErrorResponse<Object> response = CommonErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.error(ex.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
