package com.example.samplecrud.etc.exception.exceptions;

import com.example.samplecrud.etc.utils.StringUtils;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{
	private final String invalidParams;
	public BadRequestException(String message, Object params) {
			super(message);
			this.invalidParams = StringUtils.objectToJsonString(params);
	}


}
