package com.apptek.customer.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Telefone;
import com.apptek.customer.services.validation.ClienteUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@ClienteUpdate
public class TelefoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private Boolean contato;

	private String ddd;
	
	private String numero;
	
	private String ramal;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Cliente_userOperacao")
	private ClienteDTO userOperacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Cliente_id")
	private ClienteDTO cliente;
	
	@ManyToOne
	@JoinColumn(name="TipoTelefone_id")
	private TipoTelefoneDTO tipoTelefone;
	
	public TelefoneDTO() {
	}

	public TelefoneDTO(Telefone telefone) {
		this.codigoPais = telefone.getCodigoPais();
		this.numero = telefone.getNumero();
		this.ramal= telefone.getRamal();
		this.ddd = telefone.getDdd();
		this.contato = telefone.getContato();
		this.tipoTelefone = new TipoTelefoneDTO(telefone.getTipoTelefone());
		this.userOperacao = new ClienteDTO(telefone.getUserOperacao());
		// this.cliente = new ClienteDTO(telefone.getCliente());
	}	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Boolean getContato() {
		return contato;
	}

	public void setContato(Boolean contato) {
		this.contato = contato;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public ClienteDTO getUserOperacao() {
		return userOperacao;
	}

	public void setUserOperacao(ClienteDTO userOperacao) {
		this.userOperacao = userOperacao;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public TipoTelefoneDTO getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefoneDTO tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}