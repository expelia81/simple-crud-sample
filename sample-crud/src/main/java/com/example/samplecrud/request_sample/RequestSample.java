package com.example.samplecrud.request_sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// webClient를 사용한 요청 예시로, 패키지 구조 생략.
@RestController
@RequiredArgsConstructor
@RequestMapping("/webclient")
@Slf4j
public class RequestSample {
	private final WebClient webClient;

	@GetMapping("/request")
	public Mono<String> request() {
		return webClient.get()
						.uri("http://date.jsontest.com/")
						.retrieve()
						.onStatus(HttpStatusCode::is4xxClientError,
								clientResponse -> Mono.error(new RuntimeException(clientResponse.statusCode().value()+" error."))
						)
						.onStatus(HttpStatusCode::is5xxServerError,
										clientResponse -> Mono.error(new RuntimeException(clientResponse.statusCode().value()+" error."))

						)
						.bodyToMono(String.class)
						.doOnSuccess(s -> log.info("response: {}", s));
	}
}
