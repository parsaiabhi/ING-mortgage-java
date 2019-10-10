package com.mortgage.ing.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mortgage.ing.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IngMortgageException.class)
	public ResponseEntity<ResponseDto> mortgageExceptionHandler(IngMortgageException ex, WebRequest request) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ex.getMessage());
		responseDto.setStatusCode(601);
		return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<String> mortgageExceptionHandler(HttpStatusCodeException ex, WebRequest request) {
		return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());

	}

	@ExceptionHandler(NoAccountFoundException.class)
	public ResponseEntity<ErrorResponse> mortgageExceptionHandler(NoAccountFoundException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);

	}
}