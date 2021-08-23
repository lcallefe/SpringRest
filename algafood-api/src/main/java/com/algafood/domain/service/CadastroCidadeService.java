package com.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	private static final String ESTADO_INEXISTENTE_PARA_CIDADE = "Não existe cadastro de estado com código %d";

	private static final String CIDADE_INEXISTENTE = "Não existe um cadastro de cidade com código %d";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long id = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(id);
		if (!estado.isPresent()) {
			throw new EntidadeNaoEncontradaException (
				String.format(ESTADO_INEXISTENTE_PARA_CIDADE, id)); //tentando passar id cujo estado n existe
			
		}
		
		cidade.setEstado(estado.get());
		return cidadeRepository.save(cidade);
		
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(CIDADE_INEXISTENTE, cidadeId));
		}
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(CIDADE_INEXISTENTE, cidadeId)));
	}
	
	
}
