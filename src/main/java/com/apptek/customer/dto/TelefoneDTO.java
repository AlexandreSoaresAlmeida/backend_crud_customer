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

	@NotEmpty(message = "Preenchimento obrigatório")
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
		// this.tipoTelefone = new TipoTelefoneDTO(telefone.getTipoTelefone()); // aqui
		// this.userOperacao = new ClienteDTO(telefone.getUserOperacao());
		// this.cliente = new ClienteDTO(telefone.getCliente());
	}	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}