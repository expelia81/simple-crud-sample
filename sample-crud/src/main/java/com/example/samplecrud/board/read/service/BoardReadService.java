package com.example.samplecrud.board.read.service;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface BoardReadService {
	Mono<Page<Board>> findList(User user, Pageable pageable, String query);

	Mono<Board> findById(User user, Integer id);
}
