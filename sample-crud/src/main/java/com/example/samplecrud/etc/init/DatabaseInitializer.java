package com.example.samplecrud.etc.init;

import io.r2dbc.spi.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;

@Configuration
public class DatabaseInitializer{

	@Value("${spring.r2dbc.url-without-schema}")
	private String URL;
	@Value("${spring.r2dbc.username}")
	private String USERNAME;
	@Value("${spring.r2dbc.password}")
	private String PASSWORD;

	@Bean
	public ConnectionFactoryInitializer initialize() {

		ConnectionFactory factory = ConnectionFactories.get(ConnectionFactoryOptions.parse(URL)
						.mutate()
						.option(ConnectionFactoryOptions.USER, USERNAME)
						.option(ConnectionFactoryOptions.PASSWORD, PASSWORD)
						.build());

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(factory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		return initializer;
	}
}
