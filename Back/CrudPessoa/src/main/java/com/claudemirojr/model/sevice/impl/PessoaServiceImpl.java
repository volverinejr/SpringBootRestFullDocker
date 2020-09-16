package com.claudemirojr.model.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudemirojr.exception.NotFoundException;
import com.claudemirojr.model.ParamsRequestModel;
import com.claudemirojr.model.entity.Pessoa;
import com.claudemirojr.model.repository.PessoaRepository;
import com.claudemirojr.model.sevice.IPessoaService;

@Service
public class PessoaServiceImpl implements IPessoaService {

	@Autowired
	private PessoaRepository _repository;

	@Override
	@Transactional(readOnly = true)
	//@Cacheable(cacheNames = "Pessoa", key = "#prm")
	public Page<Pessoa> findAll(ParamsRequestModel prm) {
		System.out.println("ENTROU FINDALL.....");
		Pageable pageable = prm.toSpringPageRequest();

		return _repository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pessoa> findByNomeContaining(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		return _repository.findByNomeContaining(nome, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "Pessoa", key = "#id")
	public Pessoa FindById(Long id) {
		return _repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Registro não encontrado para id %d", id)));
	}

	@Override
	@Transactional
	public Pessoa save(Pessoa pessoa) {
		return _repository.save(pessoa);
	}

	@Override
	@Transactional
	@CacheEvict(cacheNames = "Pessoa", key = "#id")
	public void deleteById(Long id) {
		_repository.deleteById(id);
	}

}
