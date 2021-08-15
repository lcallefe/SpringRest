package com.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomizedJpaRepository<Cozinha, Long> {


}
