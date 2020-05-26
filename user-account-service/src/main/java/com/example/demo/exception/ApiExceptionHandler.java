package com.example.demo.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.transaction.TransactionSystemException;
import javax.persistence.RollbackException;

import com.example.demo.model.ApiException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	/*
	 * it's purpose is just to handle custom exceptions or even handle existing
	 * exceptions and we can customize the error that we throw the actual error to
	 * the client.
	 */
	
	
	
	//incase content not found 
	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex){
		
		HttpStatus error = HttpStatus.NOT_FOUND;
		int status = error.value();
		
		ApiException apiException = new ApiException
				(
				    ZonedDateTime.now(ZoneId.of("Asia/Kolkata")),
			       status, 
			       error,
			       ex.getMessage()
				   );
		
		return new ResponseEntity<>(apiException, error);
	}
	
	
	//incase content is not created due to some internal error
	@ExceptionHandler(value = {UserOperationServerException.class})
	public ResponseEntity<Object> handleFactorNotCreatedException(UserOperationServerException ex){
		
		HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
		int status = error.value();
		
		ApiException apiException = new ApiException
				(
				    ZonedDateTime.now(ZoneId.of("Asia/Kolkata")),
			       status, 
			       error,
			       ex.getMessage()
				   );
		
		return new ResponseEntity<>(apiException, error);
	}
	
	
	//incase of updation, constraint voilation (i.e. @PathVariable and @Requestbody)
	@ExceptionHandler({
		HttpMessageNotReadableException.class,
			BadRequestException.class,
			MissingPathVariableException.class,
			TypeMismatchException.class,
			MethodArgumentNotValidException.class,
			ConstraintViolationException.class,
			RollbackException.class,
			TransactionSystemException.class,
			HttpRequestMethodNotSupportedException.class
			})
	public ResponseEntity<Object> handleBadRequestException(Exception ex){
		HttpStatus error = null;
		if(ex instanceof HttpRequestMethodNotSupportedException) {
			 error = HttpStatus.METHOD_NOT_ALLOWED;
		}
		else {
		error = HttpStatus.BAD_REQUEST;
		}
		int status = error.value();
		ApiException apiException = new ApiException
				(
				    ZonedDateTime.now(ZoneId.of("Asia/Kolkata")),
			       status, 
			       error,
			       ex.getMessage()
				   );
		
		return new ResponseEntity<>(apiException, error);
	}
	
	
	@ExceptionHandler(value = {TokenNotValidatedException.class})
    public ResponseEntity<Object> handleTokenNotValidatedException(Exception ex){
		
		HttpStatus error = HttpStatus.UNAUTHORIZED;
		int status = error.value();
		
		ApiException apiException = new ApiException
				(
				    ZonedDateTime.now(ZoneId.of("Asia/Kolkata")),
			       status, 
			       error,
			       ex.getMessage()
				   );
		
		return new ResponseEntity<>(apiException, error);
	}
	
	
}
