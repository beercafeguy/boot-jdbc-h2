package com.github.beercafeguy.bootjdbch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootJdbcH2Application implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentJdbcRepository repository;

	@Override
	public void run(String... strings) throws Exception {

		logger.info("User id 10001 -> {}", repository.findById(10001L));
		logger.info("All users -> {}", repository.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(BootJdbcH2Application.class, args);
	}
}
