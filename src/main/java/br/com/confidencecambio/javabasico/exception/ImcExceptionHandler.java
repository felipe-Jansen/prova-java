package br.com.confidencecambio.javabasico.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ImcExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;

	public ImcExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler({ ImcInvalidParams.class })
	public ResponseEntity<Object> handleImcInvalidValue(ImcInvalidParams ex, WebRequest request) {
		String userMessage = messageSource.getMessage("imc.invalid-value", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		List<Error> errors = List.of(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = createListOfErrors(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, status, request);
	}

	private List<Error> createListOfErrors(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();
			errors.add(new Error(userMessage, developerMessage));
		}
			
		return errors;
	}
	
	public static class Error {
		
		private String userMessage;
		private String developerMessage;
		
		public Error(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}
		
	}    
    
}
