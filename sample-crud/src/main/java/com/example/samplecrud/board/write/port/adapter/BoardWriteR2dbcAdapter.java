package com.example.samplecrud.board.write.port.adapter;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.persistence.entity.BoardEntity;
import com.example.samplecrud.board.persistence.repository.BoardRepository;
import com.example.samplecrud.board.write.port.BoardWritePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BoardWriteR2dbcAdapter implements BoardWritePort {
	private final BoardRepository boardRepository;


	@Override
	public Mono<Board> write(Board board) {
		return boardRepository.save(BoardEntity.toEntity(board))
						.map(entity -> board.registerId(entity.getId()));
	}

	@Override
	public Mono<Void> delete(Board board) {
		return boardRepository.delete(BoardEntity.toEntity(board));
	}
}
