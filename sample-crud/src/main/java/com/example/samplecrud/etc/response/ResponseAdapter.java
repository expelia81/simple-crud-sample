package com.example.samplecrud.etc.response;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseAdapter {
	public static <T> ResponseEntity<Response<T>> single(Status status, T data) {
		return ResponseEntity.status(status.getStatus())
						.body(Response.of(status, data));
	}

	public static <T> ResponseEntity<Response<List<T>>> page(Status status, Page<T> data) {
		return ResponseEntity.status(status.getStatus())
						.body(Response.of(status, data));
	}
}
