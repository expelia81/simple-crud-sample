package com.example.samplecrud.board.write.port.adapter;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.write.port.BoardWritePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Component
@Slf4j
@RequiredArgsConstructor
public class BoardWriteKafkaAdapter implements BoardWritePort {
//	private final ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public Mono<Board> write(Board board) {
//		return reactiveKafkaProducerTemplate.send("board", objectMapper.writeValueAsString(board))
//				.then()
//				.onErrorResume(e -> {
//					log.error("Kafka send error", e);
//					return Mono.empty();
//				});
		return null;
	}

	@Override
	public Mono<Void> delete(Board board) {
		return null;
	}

}
