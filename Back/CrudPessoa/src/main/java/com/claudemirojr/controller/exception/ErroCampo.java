package com.claudemirojr.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErroCampo {
	
	private String campo;
	private String mensagem;	

}
