package com.example.samplecrud.user.domain;

import com.example.samplecrud.etc.r2dbc_orm.annotations.R2dbcTable;
import com.example.samplecrud.etc.user_resolver.RandomNameGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@R2dbcTable(name = "user", alias = "user")
@EqualsAndHashCode(of = "id")
public class User {
	@Id
	private String id;
	private String name;
	@JsonIgnore
	private String ip;

	public static User create(String ip) {
		User user = new User();
		user.name = RandomNameGenerator.getRandomBirdName();
		user.ip = ip;
		return user;
	}

	public boolean isSameUser(User user) {
		if (user == null) return false;
		return this.id.equals(user.id);
	}
}
