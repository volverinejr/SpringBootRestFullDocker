package com.claudemirojr.model.sevice;

import org.springframework.data.domain.Page;

import com.claudemirojr.model.ParamsRequestModel;
import com.claudemirojr.model.entity.Pessoa;

public interface IPessoaService {
	
	public Page<Pessoa> findAll(ParamsRequestModel prm);
	
	public Page<Pessoa> findByNomeContaining(String nome, ParamsRequestModel prm);

	public Pessoa FindById(Long id);
	
	public Pessoa save(Pessoa pessoa);
	
	public void deleteById(Long id);	
	
}
