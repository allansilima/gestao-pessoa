package com.redpill.gp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redpill.gp.domain.Pessoa;

@Repository
public interface PessoaRepositoy extends JpaRepository<Pessoa, Long> {
	
	public Optional<Pessoa> findByIdentificador(String identificador);

}
