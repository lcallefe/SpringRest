package com.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //não instanciar uma implementação dessa interface
public interface CustomizedJpaRepository<T, ID> extends JpaRepository <T, ID> {
	
	Optional<T> buscarPrimeiro();

}
