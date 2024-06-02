package com.example.samplecrud.board.read.port;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface BoardReadPort {
	Mono<Page<Board>> findList(Pageable pageable, String query);

	Mono<Board> findById(Integer id);
}
