package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

public class EstadoRepositoryImpl implements EstadoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class)
				  .getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	public void remover(Estado estado) {
		estado = buscar(estado.getId());
		manager.remove(estado);
		
	}

}
