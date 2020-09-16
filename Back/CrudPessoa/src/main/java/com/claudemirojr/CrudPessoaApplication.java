package com.claudemirojr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudPessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudPessoaApplication.class, args);
	}
	
	/*
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
	String result = bCryptPasswordEncoder.encode("admin123");
	System.out.println("My hash " + result);
	*/	

}
