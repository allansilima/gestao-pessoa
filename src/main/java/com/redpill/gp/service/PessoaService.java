package com.redpill.gp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.redpill.gp.domain.Pessoa;
import com.redpill.gp.exception.BusinessException;
import com.redpill.gp.repository.PessoaRepositoy;
import com.redpill.gp.util.Constants;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepositoy pessoaRepository;

	@Transactional
	public Pessoa create(Pessoa pessoa) {
		if (existeIdentificador(pessoa.getIdentificador())) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, Constants.IDENTIFICADOR_EXISTENTE);
		}

		return pessoaRepository.save(pessoa);
	}

	public Pessoa findByIdentificador(String identificador) {
		return pessoaRepository.findByIdentificador(identificador)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, Constants.IDENTIFICADOR_NAO_ENCONTRADO));
	}

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	private boolean existeIdentificador(String identificador) {
		return pessoaRepository.findByIdentificador(identificador).isPresent();
	}
}
