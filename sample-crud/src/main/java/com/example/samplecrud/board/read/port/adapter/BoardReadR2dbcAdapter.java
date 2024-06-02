package com.example.samplecrud.board.read.port.adapter;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.read.port.BoardReadPort;
import com.example.samplecrud.etc.r2dbc_orm.query.R2dbcORM;
import com.example.samplecrud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BoardReadR2dbcAdapter implements BoardReadPort {
	private final DatabaseClient databaseClient;
	@Override
	public Mono<Page<Board>> findList(Pageable pageable, String query) {
		return R2dbcORM.findByFilter(Board.class, databaseClient,Map.of("like,title", query), pageable);
	}

	@Override
	public Mono<Board> findById(Integer id) {
		return R2dbcORM.findById(Board.class, databaseClient, String.valueOf(id));
	}
}
