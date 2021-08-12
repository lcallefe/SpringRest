package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	//metodo usado pra criar e atualizar
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
		
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.remover(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
		}
		
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida pois pertence a um restaurante", cozinhaId));
		}
	}

}
