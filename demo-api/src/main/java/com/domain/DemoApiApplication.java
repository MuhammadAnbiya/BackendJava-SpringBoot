package com.domain;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.domain.utils.AuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // auditorAware bean sebagai AuditorAware yang digunakan untuk menentukan auditor 
public class DemoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args); // Main method untuk running aplikasi SpringBoot nya
	}

	@Bean // Anotasi Bean 
	public ModelMapper modelMapper(){ // modelMapper() Menghasilkan instance dari ModelMapper
		return new ModelMapper();
	}

	@Bean
	public AuditorAware<String> auditorAware(){ // auditorAware() Menghasilkan instance dari AuditorAwareImpl
		return new AuditorAwareImpl();
	}
}
