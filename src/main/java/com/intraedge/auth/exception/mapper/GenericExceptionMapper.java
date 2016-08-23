package com.intraedge.auth.exception.mapper;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intraedge.auth.exception.EntityAlreadyExistsException;
import com.intraedge.auth.exception.EntityNotFoundException;

@ControllerAdvice
@RestController
public class GenericExceptionMapper extends ResponseEntityExceptionHandler {
	private final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);
	@ExceptionHandler(Throwable.class)
	void handleControllerException(Throwable exception, HttpServletResponse response) throws IOException {
		log.error("handleControllerException: ", exception);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(exception instanceof EntityNotFoundException){
			response.sendError(HttpStatus.NOT_FOUND.value());
		} else if(exception instanceof EntityAlreadyExistsException){
			response.sendError(HttpStatus.CONFLICT.value());
		} else {
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
}
