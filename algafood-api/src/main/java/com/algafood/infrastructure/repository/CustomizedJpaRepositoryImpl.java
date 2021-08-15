package com.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algafood.domain.repository.CustomizedJpaRepository;

public class CustomizedJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
		implements CustomizedJpaRepository<T, ID>{
	
	private EntityManager manager;

	public CustomizedJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "from " + getDomainClass().getName(); //getDomainClass() retorna o nome da classe a qual o repo está associado
		
		T Entity = manager.createQuery(jpql, getDomainClass())
			   .setMaxResults(1)
			   .getSingleResult();
		
		return Optional.of(Entity);
	}
	
	

}
