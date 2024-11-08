package com.eliasshallouf.examples.library_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.eliasshallouf.examples.library_management_system.domain.model.repository")
@EntityScan("com.eliasshallouf.examples.library_management_system.domain.model")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public AppLoggingAspect appLoggingAspect() {
		return new AppLoggingAspect();
	}
}
