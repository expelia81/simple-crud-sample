package com.example.samplecrud.etc.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.netty.handler.codec.http.HttpResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.common.domain
 * fileName       : Response
 * author         : jeongheonjin
 * date           : 2023/04/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/06        jeongheonjin       최초 생성
 */

@NoArgsConstructor
@Getter
@Slf4j
public class Response<T> {
	private String returnCode;

	private String returnMessage;

	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Timestamp timestamp;

	private T data;

	private Integer totalPage;
	private Integer currentPage;
	private Long totalCount;
	private Integer pageSize;

	/**
	 * 페이징 없는 응답
	 */
	public static <T> Response<T> of(Status status, T data) {
		Response<T> response = new Response<>();
		response.returnCode = String.valueOf(status.getStatus());
		response.returnMessage = status.getReturnMessage();
		response.data = data;
		response.timestamp = Timestamp.from(Instant.now());
		return response;
	}
	/**
	 * 페이징 사용한 응답
	 */
	public static <T> Response<List<T>> of(Status status, Page<T> page) {
		Response<List<T>> response = Response.of(status, page.getContent());
		response.totalPage=page.getTotalPages();
		response.currentPage=page.getNumber();
		response.totalCount=page.getTotalElements();
		response.pageSize=page.getSize();
		return response;
	}

}

