package com.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar (@PathVariable Long restauranteId) {
		return  cadastroRestaurante.buscarOuFalhar(restauranteId);
	}
	
	@GetMapping ("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		/*var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		*/
		
		/*
		return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
				.and(RestauranteSpecs.comNomeSemelhante(nome))); */
		
		//em vez de passar dois metodos estaticos como parametro, vc passa só 
		//o método implementado no repositório customizado, o que evita ficar duplicando codigo por aí
		
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> buscarPrimeiro () {
		return restauranteRepository.buscarPrimeiro(); //usando customizedJpaRepository
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
			return cadastroRestaurante.salvar(restaurante);
	}
	
	@PutMapping("/{restauranteId}") 
	public Restaurante atualizar(@PathVariable Long restauranteId, 
		   @RequestBody Restaurante restaurante){
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", 
					"formasPagamento", "endereco", "dataCadastro"); 
			//ignora a copia da propriedade id
			//formasPagamento não vai ser subistuído/deletado quando o restaurante for atualizado no banco
				return cadastroRestaurante.salvar(restaurante);
	}
	
	@DeleteMapping("/{restauranteId}")
	public void remover(@PathVariable Long restauranteId){
		cadastroRestaurante.excluir(restauranteId);
	}

}
