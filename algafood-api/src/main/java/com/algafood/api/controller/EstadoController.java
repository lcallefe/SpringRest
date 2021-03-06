package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public Estado buscar (@PathVariable Long estadoId) {
		return cadastroEstado.buscarOuFalhar(estadoId);

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Estado estado) {
		cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/{estadoId}") 
	public Estado atualizar(@PathVariable Long estadoId, 
			@RequestBody Estado estado)
	{
			Estado estadoAtual = buscar(estadoId);
			BeanUtils.copyProperties(estado, estadoAtual, "id"); //ignora a copia da propriedade id
			return cadastroEstado.salvar(estado);

	}
	
	@DeleteMapping("/{estadoId}")
	public void remover(@PathVariable Long estadoId)
	{
		 cadastroEstado.excluir(estadoId);
	}
	
}
