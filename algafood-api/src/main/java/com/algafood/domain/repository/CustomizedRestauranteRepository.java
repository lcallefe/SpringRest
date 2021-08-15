package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algafood.domain.model.Restaurante;

public interface CustomizedRestauranteRepository {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	/* 
	 * ajuda a evitar erros em tempo de execução, 
	 * não é possível alterar o nome do método no controlador
	 */

	List<Restaurante> findComFreteGratis(String nome);
}