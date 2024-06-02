package com.example.samplecrud.user.persistence.repository;

import com.example.samplecrud.user.persistence.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface UserRepository extends R2dbcRepository<UserEntity, String> {
	Mono<UserEntity> findByIp(String ip);
}
