package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;

public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class)
				  .getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
	}
	

}
