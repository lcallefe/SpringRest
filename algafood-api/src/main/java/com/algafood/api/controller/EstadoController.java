package com.algafood.api.controller;

import java.util.List;

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

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar (@PathVariable Long estadoId) {
		Estado estado = estadoRepository.buscar(estadoId);
		if (estado !=null) {
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
		try {
			estado = cadastroEstado.salvar(estado);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
								 .body(e.getMessage());
			
		}
	}
	
	@PutMapping("/{estadoId}") 
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, 
			@RequestBody Estado estado)
	{
		try {
			Estado estadoAtual = estadoRepository.buscar(estadoId);
			if (estadoAtual != null) {
				BeanUtils.copyProperties(estado, estadoAtual, "id"); //ignora a copia da propriedade id
				cadastroEstado.salvar(estado);
				return ResponseEntity.ok(estadoAtual);
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					 .body(e.getMessage());

		}
		
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId)
	{
		 
		try {
			cadastroEstado.excluir(estadoId);
			return ResponseEntity.ok().build();
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			//return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	
	}
	
}
