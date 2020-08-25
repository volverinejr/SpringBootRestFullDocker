package com.claudemirojr.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.model.entity.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);

}
