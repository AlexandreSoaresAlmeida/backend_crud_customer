package com.apptek.customer.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Cidade;
import com.apptek.customer.model.Estado;
import com.apptek.customer.services.validation.ClienteUpdate;

@ClienteUpdate
public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@ManyToOne
	@JoinColumn(name="Estado_id")
	private EstadoDTO estado;
	
	
	public CidadeDTO() {
	}

	public CidadeDTO(Cidade cidade) {
		this.nome = cidade.getNome();
		this.estado = new EstadoDTO(cidade.getEstado());		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}