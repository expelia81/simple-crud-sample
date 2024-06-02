package com.example.samplecrud.board.write.service;

import com.example.samplecrud.board.command.BoardCommand;
import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.user.domain.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface BoardWriteService {
	Mono<Board> write(User user, BoardCommand command);

	Mono<Void> delete(User user, Integer id);

	Mono<Board> modify(User user, Integer id, BoardCommand command);
}
