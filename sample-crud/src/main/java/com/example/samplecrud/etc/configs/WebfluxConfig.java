package com.example.samplecrud.etc.configs;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class WebfluxConfig {
	@Bean
	public WebFluxConfigurer corsConfigurer() {
		return new WebFluxConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/**")
								.allowedOrigins("*")
								.allowedMethods("*")
								.allowedHeaders("*");
			}
			@Override
			public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
				ReactivePageableHandlerMethodArgumentResolver pageableArgumentResolver =
								new ReactivePageableHandlerMethodArgumentResolver();
				pageableArgumentResolver.setMaxPageSize(Integer.MAX_VALUE);
				configurer.addCustomResolver(pageableArgumentResolver);
			}

		};

	}

}