package com.example.samplecrud.etc.exception.handler;

import com.example.samplecrud.etc.exception.exceptions.BadRequestException;
import com.example.samplecrud.etc.exception.exceptions.NotHasAuthorityException;
import com.example.samplecrud.etc.r2dbc_orm.exception.common.CommonExceptions;
import com.example.samplecrud.etc.response.Response;
import com.example.samplecrud.etc.response.ResponseAdapter;
import com.example.samplecrud.etc.response.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHadler {

	private Mono<ResponseEntity<Response<Object>>> commonResponse(
					Status status, RuntimeException e
	) {
		log.error("{} : {}", e.getClass(), e.getMessage());
		return Mono.just(ResponseAdapter.single(status, e.getLocalizedMessage()));
	}
	@ExceptionHandler(BadRequestException.class)
	public Mono<ResponseEntity<Response<Object>>> badRequestException(BadRequestException e) {
		return commonResponse(Status.BAD_REQUEST, e);
	}

	@ExceptionHandler(CommonExceptions.DataNotFoundException.class)
	public Mono<ResponseEntity<Response<Object>>> dataNotFoundException(CommonExceptions.DataNotFoundException e) {
		return commonResponse(Status.NOT_FOUND, e);
	}

	@ExceptionHandler(NotHasAuthorityException.class)
	public Mono<ResponseEntity<Response<Object>>> notHasAuthorityException(NotHasAuthorityException e) {
		return commonResponse(Status.FORBIDDEN, e);
	}
}
