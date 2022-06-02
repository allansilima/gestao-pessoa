package com.redpill.gp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redpill.gp.domain.Pessoa;
import com.redpill.gp.domain.dto.form.PessoaForm;
import com.redpill.gp.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pessoas")
@Api(tags = { "Pessoa" })
public class PessoaController {

	@Autowired
	private PessoaService service;

	@ApiOperation(value = "Salvar pessoa")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Registro criado com sucesso"),
			@ApiResponse(code = 400, message = "Erro ao tentar criar o registro"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor ao processar requisição") })
	@PostMapping
	public ResponseEntity<Pessoa> create(@Valid @RequestBody PessoaForm pessoaForm) {
		var pessoa = Pessoa.create(pessoaForm.getNome(), pessoaForm.getIdentificador());

		pessoa = service.create(pessoa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
	}

	@ApiOperation(value = "Pesquisar pessoa por identificador CPF/CNPJ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = 400, message = "Erro ao tentar pesquisar o registro"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor ao processar requisição") })
	@GetMapping
	public ResponseEntity<Pessoa> findByIdentificador(@RequestParam(required = true) String identificador) {
		var pessoa = service.findByIdentificador(identificador);
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}

	@ApiOperation(value = "Pesquisar todas pessoas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pesquisa realizada com sucesso"),
			@ApiResponse(code = 400, message = "Erro ao tentar pesquisar o registro"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor ao processar requisição") })
	@GetMapping("/listar")
	public ResponseEntity<List<Pessoa>> findAll() {
		var pessoa = service.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}
}
