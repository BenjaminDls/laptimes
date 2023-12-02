package fr.benjamindanlos.laptimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ComponentScan
@EnableAsync
public class LaptimesApplication {
	public static void main(String[] args) {
		SpringApplication.run(LaptimesApplication.class, args);
	}
}
