package com.memory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication(scanBasePackages = "com.memory", exclude = {JacksonAutoConfiguration.class})
public class MemoryApplication extends WebMvcConfigurationSupport {
	public static void main(String[] args) {
		SpringApplication.run(MemoryApplication.class, args);
	}
}

