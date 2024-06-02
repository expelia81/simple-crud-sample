package com.example.samplecrud.board.read.controller;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.read.service.BoardReadService;
import com.example.samplecrud.etc.response.Response;
import com.example.samplecrud.etc.response.ResponseAdapter;
import com.example.samplecrud.etc.response.Status;
import com.example.samplecrud.etc.user_resolver.CurrentUser;
import com.example.samplecrud.user.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardReadController {
	private final BoardReadService service;

	@GetMapping("/list")
	public Mono<ResponseEntity<Response<List<Board>>>> list(@CurrentUser @Parameter(hidden = true) User user,
																													@PageableDefault @Parameter(hidden = true) Pageable pageable,
																													@RequestParam(required = false, defaultValue = "") String query) {
		return service.findList(user, pageable, query)
			.map(data -> ResponseAdapter.page(Status.SUCCESS, data));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Response<Board>>> get(@CurrentUser @Parameter(hidden = true) User user,
													@PathVariable Integer id) {
		return service.findById(user, id)
//						.doOnSuccess(data -> log.info("data: {}", data.getContents()))
						.map(data -> ResponseAdapter.single(Status.SUCCESS, data))
						;
	}
}
