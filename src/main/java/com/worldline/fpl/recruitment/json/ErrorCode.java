package com.worldline.fpl.recruitment.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

/**
 * Error code
 * 
 * @author A525125
 *
 */
@AllArgsConstructor
public enum ErrorCode {

	INVALID_ACCOUNT(HttpStatus.NOT_FOUND);

	@Getter
	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
