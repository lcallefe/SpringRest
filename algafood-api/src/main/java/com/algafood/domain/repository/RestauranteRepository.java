package com.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomizedJpaRepository<Restaurante, Long>, CustomizedRestauranteRepository, JpaSpecificationExecutor<Restaurante> {
	@Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
	List<Restaurante> findAll();
}
