package com.redpill.gp.mock;

import com.redpill.gp.domain.Pessoa;

public class PessoaMock {

	public static Pessoa create() {
		return Pessoa.builder()
				.codigo(1L).nome("Teste")
				.identificador("12345678901")
				.build();
	}
}