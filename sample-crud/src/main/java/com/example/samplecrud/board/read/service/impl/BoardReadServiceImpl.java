package com.example.samplecrud.board.read.service.impl;

import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.read.port.BoardReadPort;
import com.example.samplecrud.board.read.service.BoardReadService;
import com.example.samplecrud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BoardReadServiceImpl implements BoardReadService {

	private final BoardReadPort boardReadPort;


	@Override
	public Mono<Page<Board>> findList(User user, Pageable pageable, String query) {
		return boardReadPort.findList(pageable, query);
	}

	@Override
	public Mono<Board> findById(User user, Integer id) {
		return boardReadPort.findById(id);
	}
}
