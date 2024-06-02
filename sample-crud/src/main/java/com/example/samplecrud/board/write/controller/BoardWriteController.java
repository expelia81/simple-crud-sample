package com.example.samplecrud.board.write.controller;

import com.example.samplecrud.board.command.BoardCommand;
import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.write.service.BoardWriteService;
import com.example.samplecrud.etc.response.Response;
import com.example.samplecrud.etc.response.ResponseAdapter;
import com.example.samplecrud.etc.response.Status;
import com.example.samplecrud.etc.user_resolver.CurrentUser;
import com.example.samplecrud.user.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardWriteController {
	private final BoardWriteService service;

	@PostMapping("")
	public Mono<ResponseEntity<Response<Board>>> write(
					@CurrentUser @Parameter(hidden = true) User user,
					@RequestBody BoardCommand command) {
		return service.write(user, command)
						.map(data -> ResponseAdapter.single(Status.SUCCESS, data));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Response<Board>>> modify(
					@CurrentUser @Parameter(hidden = true) User user,
					@PathVariable Integer id,
					@RequestBody BoardCommand command) {
		return service.modify(user, id, command)
						.map(data -> ResponseAdapter.single(Status.SUCCESS, data));
	}
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Response<Void>>> delete(
					@CurrentUser @Parameter(hidden = true) User user,
					@PathVariable Integer id) {
		return service.delete(user, id)
						.thenReturn(ResponseAdapter.single(Status.SUCCESS, null));
	}
}
