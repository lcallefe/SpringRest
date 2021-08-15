package com.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.infrastructure.repository.spec.RestauranteSpecs;

@Repository
public class RestauranteRepositoryImpl {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> find (String nome, 
		   BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		/*var jpql = "from Restaurante where nome like :nome "
				+ "and taxaFrete between :taxaInicial and :taxaFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
					  .setParameter("nome", "%" + nome + "%")
					  .setParameter("taxaInicial", taxaFreteInicial)
					  .setParameter("taxaFinal", taxaFreteFinal)
					  .getResultList();*/
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
			
		}
		
		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
			
		}
		
		/*
		Predicate nomePredicate = builder.like(root.get("mome"), "%" + nome + "%");
		
		Predicate taxaInicialPredicate = builder
				  .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		
		Predicate taxaFinalPredicate = builder
				  .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
		
		*/
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query  = manager.createQuery(criteria);
		
		return query.getResultList();	
	}
	
	public List<Restaurante> findComFreteGratis (String nome) {
		return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
			   .and(RestauranteSpecs.comNomeSemelhante(nome)));
	}

}
