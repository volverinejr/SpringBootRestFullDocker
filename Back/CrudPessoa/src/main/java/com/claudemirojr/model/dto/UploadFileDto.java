package com.claudemirojr.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String uri;
	private String type;
	private Long size;

}
