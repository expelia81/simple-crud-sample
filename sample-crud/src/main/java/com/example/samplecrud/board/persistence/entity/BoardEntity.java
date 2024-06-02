package com.example.samplecrud.board.persistence.entity;

import com.example.samplecrud.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Table("board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
	@Id
	private Integer id;
	private String title;
	private String contents;
	private LocalDateTime createTime;
	private LocalDateTime updatedTime;
	private String createUserId;

	public static BoardEntity toEntity(Board board) {
		return BoardEntity.builder()
				.id(board.getId())
				.title(board.getTitle())
				.contents(board.getContents())
				.createTime(board.getCreateTime())
				.updatedTime(board.getUpdatedTime())
				.createUserId(board.getCreateUser().getId())
				.build();
	}

}
