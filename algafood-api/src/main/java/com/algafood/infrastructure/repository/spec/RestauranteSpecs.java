package com.algafood.infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.algafood.domain.model.Restaurante;

public class RestauranteSpecs {
	
	public static Specification<Restaurante> comFreteGratis() {
		//return (root, query, builder) ->
			//builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
		return new RestauranteComFreteGratisSpec();
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return new RestauranteComNomeSemelhanteSpec(nome);
	}

}
