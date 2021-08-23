package com.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="subtotal", nullable = false)
	private BigDecimal subtotal;
	@Column (name="taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	@Column (name="valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	private StatusPedido status;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	
	@UpdateTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="cliente_id", nullable = false)
	private Usuario cliente;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
	
}
