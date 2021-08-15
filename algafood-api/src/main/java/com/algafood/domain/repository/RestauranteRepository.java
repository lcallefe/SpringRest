package com.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomizedJpaRepository<Restaurante, Long>, CustomizedRestauranteRepository, JpaSpecificationExecutor<Restaurante> {

}
