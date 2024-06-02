package com.example.samplecrud.board.write.service.impl;

import com.example.samplecrud.board.command.BoardCommand;
import com.example.samplecrud.board.domain.Board;
import com.example.samplecrud.board.read.port.BoardReadPort;
import com.example.samplecrud.board.write.port.BoardWritePort;
import com.example.samplecrud.board.write.service.BoardWriteService;
import com.example.samplecrud.etc.exception.exceptions.NotHasAuthorityException;
import com.example.samplecrud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BoardWriteServiceImpl implements BoardWriteService {
	private final BoardWritePort boardWritePort;
	private final BoardReadPort boardReadPort;

	@Override
//	@Transactional 딱히 연결되는 트랜잭션이 없음
	public Mono<Board> write(User user, BoardCommand command) {
		return Mono.fromCallable(() -> Board.create(user, command))
							 .flatMap(boardWritePort::write);
	}

	@Override
//	@Transactional 딱히 연결되는 트랜잭션이 없음
	public Mono<Void> delete(User user, Integer id) {
		return boardReadPort.findById(id)
						.filter(board -> user.isSameUser(board.getCreateUser()))
						.switchIfEmpty(Mono.error(new NotHasAuthorityException("본인의 게시글이 아닙니다. 해당 게시글을 삭제할 수 없습니다.")))
						.flatMap(boardWritePort::delete)
						;
	}

	@Override
	public Mono<Board> modify(User user, Integer id, BoardCommand command) {
		return boardReadPort.findById(id)
						.filter(board -> user.isSameUser(board.getCreateUser()))
						.switchIfEmpty(Mono.error(new NotHasAuthorityException("본인의 게시글이 아닙니다. 해당 게시글을 수정할 수 없습니다.")))
						.map(board -> board.modify(command))
						.flatMap(boardWritePort::write)
						;
	}
}
