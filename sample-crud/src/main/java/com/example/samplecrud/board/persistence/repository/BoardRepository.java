package com.example.samplecrud.board.persistence.repository;

import com.example.samplecrud.board.persistence.entity.BoardEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

@Component
public interface BoardRepository extends R2dbcRepository<BoardEntity, String> {
}
