package com.example.samplecrud.user.persistence.entity;

import com.example.samplecrud.user.domain.User;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
@Builder
public class UserEntity {
	@Id
	private String id;
	private String name;
	private String ip;


	public static UserEntity from(User user) {
		return UserEntity.builder()
				.id(user.getId())
				.name(user.getName())
				.ip(user.getIp())
				.build();
	}
	public User toDomain() {
		return User.builder()
				.id(this.id)
				.name(this.name)
				.ip(this.ip)
				.build();
	}
}
