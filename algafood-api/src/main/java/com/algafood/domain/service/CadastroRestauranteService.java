package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	//metodo usado pra criar e atualizar
	public Restaurante salvar(Restaurante restaurante) {
		Long id = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(id);
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException (
				String.format("Não existe cadastro de cozinha com código %d", id)); //tentando passar id cuja cozinha n existe
			
		}
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.salvar(restaurante);
		
	}

}
