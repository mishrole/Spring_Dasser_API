package com.dasser.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DasserApiApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(DasserApiApplication.class, args);
	}
	
	// Encrypted PWD (Test)
	@Override
	public void run (String... args) throws Exception {
		String password = "1234";
		String passwordBcrypt = passwordEncoder.encode(password);
		System.out.println("-------------------");
		System.out.println(passwordBcrypt);
		System.out.println("-------------------");
	}

}
