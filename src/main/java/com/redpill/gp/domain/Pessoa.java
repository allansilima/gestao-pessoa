package com.redpill.gp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import com.redpill.gp.domain.enums.TipoIdentificadorEnum;
import com.redpill.gp.exception.BusinessException;
import com.redpill.gp.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 6807919651521736752L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@NotEmpty(message = "Nome é obrigatório")
	@Column(name = "nome", length = 50)
	private String nome;

	@Column(name = "identificador", length = 14)
	@NotNull(message = "Identificador é obrigatório")
	private Long identificador;

	@NotNull(message = "Tipo identificador é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_identificador")
	private TipoIdentificadorEnum tipoIdentificador;


	public static Pessoa create(String nome, Long identificador) {
		var digIdentificador = identificador.toString().length();
		
		switch (digIdentificador) {
		case 11:
			return Pessoa.builder().nome(nome).identificador(identificador).tipoIdentificador(TipoIdentificadorEnum.CPF).build();
		case 14:
			return Pessoa.builder().nome(nome).identificador(identificador).tipoIdentificador(TipoIdentificadorEnum.CNPJ).build();
		default:
			throw new BusinessException(HttpStatus.BAD_REQUEST, Constants.IDENTIFICADOR_TAMANHO);
		}
	}
}
