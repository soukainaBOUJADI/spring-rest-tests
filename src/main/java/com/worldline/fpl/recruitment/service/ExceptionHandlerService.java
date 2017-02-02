package com.worldline.fpl.recruitment.service;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worldline.fpl.recruitment.StartBoot;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorResponse;

/**
 * Exception handler
 * 
 * @author A525125
 *
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerService {
	
	private static final Logger log = LoggerFactory.getLogger(StartBoot.class);

	/**
	 * Handle {@link ServiceException}
	 * 
	 * @param e
	 *            exception
	 * @return error response
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleServiceException(
			ServiceException e) {
		log.error("Error : " + e.getMessage());
		return ResponseEntity.status(e.getErrorCode().getHttpStatus().value())
				.body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
	}
}
