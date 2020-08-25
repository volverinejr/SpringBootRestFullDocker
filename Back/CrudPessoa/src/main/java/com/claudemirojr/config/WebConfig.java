package com.claudemirojr.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class WebConfig {
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE",
				"CONNECT");
	}

}
