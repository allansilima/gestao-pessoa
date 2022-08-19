package com.redpill.gp.domain.dto.form;

import javax.validation.constraints.NotEmpty;

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
	@NotEmpty(message = "Identificador é obrigatório")
	private String identificador;
}
