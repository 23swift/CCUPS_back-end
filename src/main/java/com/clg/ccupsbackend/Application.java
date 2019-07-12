package com.clg.ccupsbackend;

import com.clg.ccupsbackend.model.DataTypeModel;
import com.clg.ccupsbackend.repository.DataTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements  CommandLineRunner {
	@Autowired
	private DataTypeRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) {
		repo.save(new DataTypeModel("Numeric","[0-9]"));
		repo.save(new DataTypeModel("Alpha Numeric","[0-9a-zA-Z]"));
		repo.save(new DataTypeModel("Date","[0-9]"));
		repo.save(new DataTypeModel("Amount","[0-9]"));

	
	
	}
}
