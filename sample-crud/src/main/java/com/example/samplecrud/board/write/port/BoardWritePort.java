package com.example.samplecrud.board.write.port;

import com.example.samplecrud.board.domain.Board;
import reactor.core.publisher.Mono;

public interface BoardWritePort {
	Mono<Board> write(Board board);

	Mono<Void> delete(Board board);
}
