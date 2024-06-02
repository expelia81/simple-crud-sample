package com.example.samplecrud.board.domain;

import com.example.samplecrud.board.command.BoardCommand;
import com.example.samplecrud.etc.r2dbc_orm.annotations.R2dbcJoinColumn;
import com.example.samplecrud.etc.r2dbc_orm.annotations.R2dbcTable;
import com.example.samplecrud.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@R2dbcTable(name = "board", alias = "board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id
	private Integer id;
	private String title;
	private String contents;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedTime;

	@R2dbcJoinColumn(name = "create_user_id")
	private User createUser;

	public static Board create(User user, BoardCommand command) {
		return Board.builder()
				.title(command.title())
				.contents(command.content())
				.createTime(LocalDateTime.now())
				.createUser(user)
				.build();
	}

	public Board registerId(Integer id) {
		this.id = id;
		return this;
	}

	public Board modify(BoardCommand command) {
		this.title = command.title()==null?this.title:command.title();
		this.contents = command.content()==null?this.contents:command.content();
		this.updatedTime = LocalDateTime.now();
		return this;
	}
}