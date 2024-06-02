package com.example.samplecrud.etc.response;

import lombok.Getter;

@Getter
public enum Status {

	SUCCESS(200, "Success"),
	BAD_REQUEST(400, "Bad Request"),
	NOT_FOUND(404, "Not Found"),
	FORBIDDEN(403, "Forbidden"),
	FAIL(500, "Fail"),

	;

	private final Integer status;
	private final String returnMessage;

	Status(int status, String returnMessage) {
		this.status = status;
		this.returnMessage = returnMessage;
	}
}
