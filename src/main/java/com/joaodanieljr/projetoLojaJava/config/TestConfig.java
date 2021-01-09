package com.joaodanieljr.projetoLojaJava.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.joaodanieljr.projetoLojaJava.services.DBService;
import com.joaodanieljr.projetoLojaJava.services.EmailService;
import com.joaodanieljr.projetoLojaJava.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean InstantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
