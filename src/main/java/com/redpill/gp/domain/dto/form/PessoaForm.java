package com.redpill.gp.domain.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PessoaForm {

	@ApiModelProperty(value = "Nome", required = true, example = "Paulo da Silva")
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@ApiModelProperty(value = "Identificador CPF/CNPJ", required = true, example = "12345678900")
	@NotNull(message = "Identificador é obrigatório")
	private Long identificador;
}
