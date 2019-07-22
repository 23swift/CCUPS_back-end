package com.clg.ccupsbackend;

import com.clg.ccupsbackend.model.DataTypeModel;
import com.clg.ccupsbackend.repository.DataTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
// public class Application extends SpringBootServletInitializer implements  CommandLineRunner {
	public class Application implements  CommandLineRunner {
	@Autowired
	private DataTypeRepository repo;

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) {
		repo.save(new DataTypeModel("Numeric","[0-9]"));
		repo.save(new DataTypeModel("Alpha Numeric","[a-zA-Z0-9]"));
		repo.save(new DataTypeModel("Date","[0-9]"));
		repo.save(new DataTypeModel("Amount","[0-9]"));
		repo.save(new DataTypeModel("Alpha & Special Characters","[a-zA-Z0-9\\s\\_\\@\\$\\&\\(\\)\\-\\[\\]\\;\\:\\,\\.\\/\\|\\\\]"));
		
		

	
	
	}
	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	//  return application.sources(Application.class);
	// }
}
