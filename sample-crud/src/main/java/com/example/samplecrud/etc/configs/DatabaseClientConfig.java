package com.example.samplecrud.etc.configs;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseClientConfig {


	@Value("${spring.r2dbc.url}")
	private String URL;
	@Value("${spring.r2dbc.username}")
	private String USERNAME;
	@Value("${spring.r2dbc.password}")
	private String PASSWORD;

	/* r2dbc pool 커스텀 테스트중. 별도 설정 없을 경우 아래 기본값으로 동작함. 30초에 한번씩 커넥션 유효성 검증을 실시한다.*/
	@Value("${spring.r2dbc.connection-pool.max-size:10}")
	private int MAX_SIZE;
	@Value("${spring.r2dbc.connection-pool.initial-size:10}")
	private int INITIAL_SIZE;
	@Value("${spring.r2dbc.connection-pool.max-idle-time:600000}")
	private int MAX_IDLE_TIME;
	@Value("${spring.r2dbc.connection-pool.max-life-time:1800000}")
	private int MAX_LIFE_TIME;
//	@Value("${spring.r2dbc.connection-pool.background-eviction-interval:30000}") //ms 단위
//	private int BACKGROUND_EVICTION_INTERVAL;

	@Bean
	public ConnectionFactory maestroConnectionFactory() {
		ConnectionFactory factory = ConnectionFactoryBuilder.withUrl(URL)
																												.username(USERNAME)
																												.password(PASSWORD)
																												.build();

		ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder(factory)
						.maxSize(MAX_SIZE)
						.initialSize(INITIAL_SIZE)
						.maxLifeTime(Duration.ofMillis(MAX_LIFE_TIME))
						.maxIdleTime(Duration.ofMillis(MAX_IDLE_TIME))
						.build();
		return new ConnectionPool(configuration);
	}
	@Bean
	public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
		return DatabaseClient.builder()
						.connectionFactory(connectionFactory)
						.build();
	}

	@Bean
	public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
		return new R2dbcTransactionManager(connectionFactory);
	}
}
