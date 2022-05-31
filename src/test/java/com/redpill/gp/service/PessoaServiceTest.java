package com.redpill.gp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.redpill.gp.domain.Pessoa;
import com.redpill.gp.exception.BusinessException;
import com.redpill.gp.mock.PessoaMock;
import com.redpill.gp.repository.PessoaRepositoy;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@InjectMocks
	private PessoaService service;
	
	@Mock
	private PessoaRepositoy repository;
	
    @Test
    @DisplayName("Quando criar pessoa com identificador existente")
    public void create_whenIdenticationExists_thenBusinessExceptionAsExpected() {
        Pessoa pessoaMock = PessoaMock.create();

        when(repository.findByIdentificador(pessoaMock.getIdentificador())).thenReturn(Optional.of(pessoaMock));

        assertThrows(BusinessException.class, () -> {
            service.create(pessoaMock);
        });

        then(repository).should(times(1)).findByIdentificador(pessoaMock.getIdentificador());
        then(repository).should(never()).save(pessoaMock);
    }	

    @Test
    @DisplayName("Quando criar pessoa com sucesso")
    public void create_whenIdenticationNotExists_thenResultAsExpected() {
        Pessoa pessoaMock = PessoaMock.create();

        when(repository.findByIdentificador(pessoaMock.getIdentificador())).thenReturn(Optional.empty());
        when(repository.save(pessoaMock)).thenReturn(pessoaMock);

        Pessoa pessoa = service.create(pessoaMock);
        
        then(repository).should(times(1)).findByIdentificador(pessoaMock.getIdentificador());
        then(repository).should(times(1)).save(pessoaMock);
        assertEquals(pessoa.getIdentificador(), pessoaMock.getIdentificador());
    }	
}
