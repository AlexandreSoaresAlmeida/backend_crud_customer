package com.apptek.customer.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Cidade;
import com.apptek.customer.model.Cliente;
import com.apptek.customer.services.validation.ClienteUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@ClienteUpdate
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;

	private String numero;

	private String complemento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String bairro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="aCidade_id")
	private Cidade cidade;
	
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(String logradouro, String numero, String complemento, String bairro, String cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento= complemento;
		this.bairro = bairro;
		this.cep = cep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}