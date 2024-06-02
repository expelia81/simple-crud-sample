package com.example.samplecrud.filters;

import com.example.samplecrud.user.domain.User;
import com.example.samplecrud.user.persistence.entity.UserEntity;
import com.example.samplecrud.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 입력된 ip로부터 User를 찾거나 생성하는 필터.
 * ip는 X-Forwarded-For 헤더를 통해 받아오거나, 없을 경우 RemoteAddress를 통해 받아온다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserFilter implements WebFilter {
	private final UserRepository userRepository;
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		if (exchange.getRequest().getURI().getPath().contains("/api-docs") ||
						exchange.getRequest().getURI().getPath().contains("/swagger") ||
						exchange.getRequest().getURI().getPath().contains("/webjars")
		) {
			return chain.filter(exchange);
		}
		return Mono.fromCallable(() -> getRemoteIp(exchange))
							 .flatMap(this::findUser)
							 .doOnSuccess(user -> exchange.getAttributes().put("user", user))
							 .then(chain.filter(exchange));
	}

	private Mono<User> findUser(String ip) {
		return userRepository.findByIp(ip)
						.switchIfEmpty(userRepository.save(UserEntity.from(User.create(ip)))
										.doOnSuccess(user -> log.info("New User created")))
						.map(UserEntity::toDomain)
						.doOnSuccess(user -> log.info("User found: {}", user))
						;
	}

	private static String getRemoteIp(ServerWebExchange exchange) {
		String x_foward_for = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
		String clientIp = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
		if (x_foward_for != null) {
			String[] ips = x_foward_for.split(",");
			if (ips.length > 0) {
				clientIp = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For").split(",")[0].trim();
			}
		}
		return clientIp;
	}
}
