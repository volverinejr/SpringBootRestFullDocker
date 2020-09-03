package com.claudemirojr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = { "InfoEndpoint" })
@SwaggerDefinition(tags = { @Tag(name = "InfoEndpoint", description = "descrição do endpoint") })
@RestController
@RequestMapping("/info/v1")

public class InfoController {

	@Value("${configuracao.perfil}")
	private String perfil;
	
	
	
	@GetMapping()
	public String perfil() {
		return perfil;
	}

}
