package com.apptek.customer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private String email;

	private Boolean contato;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss")
	private Date dtOperacao;
	
	private Cliente userOperacao;
	
	private Boolean ativado;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Cliente_id")
	private Cliente cliente;	
	
	public Email() {

	}

	public Email(String email, Boolean contato, Cliente cliente, Boolean ativado, Cliente userOperacao) {
		super();
		this.email = email;
		this.contato = contato;
		this.cliente = cliente;
		this.ativado = ativado;
		this.dtOperacao = new Date();
		this.userOperacao = userOperacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivado() {
		return ativado;
	}

	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Boolean getContato() {
		return contato;
	}

	public void setContato(Boolean contato) {
		this.contato = contato;
	}

	public Date getDtOperacao() {
		return dtOperacao;
	}

	public void setDtOperacao(Date dtOperacao) {
		this.dtOperacao = dtOperacao;
	}

	public Cliente getUserOperacao() {
		return userOperacao;
	}

	public void setUserOperacao(Cliente userOperacao) {
		this.userOperacao = userOperacao;
	}	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}