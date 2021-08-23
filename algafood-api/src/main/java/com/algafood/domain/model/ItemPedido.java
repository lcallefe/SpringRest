package com.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="quantidade", nullable = false)
	private int quantidade;
	@Column (name="preco_unitario", nullable = false)
	private BigDecimal precoUnitario;
	@Column (name="preco_total", nullable = false)
	private BigDecimal precoTotal;
	@Column 
	private String observacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pedido_id", nullable = false)
	private Pedido pedido;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="produto_id", nullable = false)
	private Produto produto;

}
