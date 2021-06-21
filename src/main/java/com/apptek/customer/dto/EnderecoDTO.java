package com.apptek.customer.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Cidade;
import com.apptek.customer.model.Cliente;
import com.apptek.customer.model.Endereco;
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
	private ClienteDTO cliente;
	
	@ManyToOne
	@JoinColumn(name="Cidade_id")
	private CidadeDTO cidade;
	
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento= endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();		
		this.cidade = new CidadeDTO(endereco.getCidade());		
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public CidadeDTO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeDTO cidade) {
		this.cidade = cidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}