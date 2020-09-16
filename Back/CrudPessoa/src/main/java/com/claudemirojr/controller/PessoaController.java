package com.claudemirojr.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claudemirojr.converter.DozerConverter;
import com.claudemirojr.model.ParamsRequestModel;
import com.claudemirojr.model.dto.PessoaDto;
import com.claudemirojr.model.entity.Pessoa;
import com.claudemirojr.model.sevice.IPessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = { "PessoaEndpoint" })
@SwaggerDefinition(tags = { @Tag(name = "PessoaEndpoint", description = "descrição do endpoint") })
@RestController
@RequestMapping("/pessoas/v1")
public class PessoaController {

	@Autowired
	private IPessoaService _service;

	@Autowired
	private PagedResourcesAssembler<PessoaDto> assembler;

	@ApiOperation(value = "Listar todas as pessoas")
	@GetMapping()
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<Pessoa> pessoasPage = _service.findAll(prm);

		Page<PessoaDto> pessoasDto = pessoasPage.map(this::conerterToPessoaDto);

		pessoasDto.stream()
				.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).FindById(p.getKey())).withSelfRel()));

		PagedModel<?> resources = assembler.toModel(pessoasDto);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "buscar pessoas pelo nome")
	@GetMapping("/findByNome/{nome}")
	public ResponseEntity<?> findByNomeContaining(@PathVariable String nome, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<Pessoa> pessoasPage = _service.findByNomeContaining(nome, prm);

		Page<PessoaDto> pessoasDto = pessoasPage.map(this::conerterToPessoaDto);

		pessoasDto.stream()
				.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).FindById(p.getKey())).withSelfRel()));

		PagedModel<?> resources = assembler.toModel(pessoasDto);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	private PessoaDto conerterToPessoaDto(Pessoa pessoa) {
		return DozerConverter.parseObject(pessoa, PessoaDto.class);
	}

	@ApiOperation(value = "Localizar uma pessoa específica")
	@GetMapping("/{id}")
	public PessoaDto FindById(@PathVariable Long id) {
		Pessoa pessoa = _service.FindById(id);
		PessoaDto pessoaDto = this.conerterToPessoaDto(pessoa);

		pessoaDto.add(linkTo(methodOn(PessoaController.class).FindById(id)).withSelfRel());

		return pessoaDto;
	}

	@ApiOperation(value = "Salvar uma pessoa no banco")
	@PostMapping
	public PessoaDto save(@RequestBody @Valid PessoaDto pessoaDto) {
		var entity = DozerConverter.parseObject(pessoaDto, Pessoa.class);

		Pessoa pessoa = _service.save(entity);

		pessoaDto = this.conerterToPessoaDto(pessoa);

		pessoaDto.add(linkTo(methodOn(PessoaController.class).FindById(pessoa.getId())).withSelfRel());

		return pessoaDto;
	}

	@ApiOperation(value = "Atualizar uma pessoa no banco")
	@PutMapping("/{id}")
	public PessoaDto update(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
		Pessoa pessoaExiste = _service.FindById(id);

		pessoaExiste.Atualizar(pessoaDto.getNome(), pessoaDto.getEmail(), pessoaDto.getEndereco());

		Pessoa pessoa = _service.save(pessoaExiste);

		pessoaDto = this.conerterToPessoaDto(pessoa);

		pessoaDto.add(linkTo(methodOn(PessoaController.class).FindById(pessoa.getId())).withSelfRel());

		return pessoaDto;
	}

	@ApiOperation(value = "Remover uma pessoa no banco")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		_service.FindById(id);

		_service.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
