package com.apptek.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.apptek.customer.model.Cliente;
import com.apptek.customer.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve estar entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")	
	private String cpf;

	@NotEmpty(message="Preenchimento obrigatório")	
	private String usuario;

	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	private Cliente usuarioOperacao;
	
	public ClienteNewDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Cliente getUsuarioOperacao() {
		return usuarioOperacao;
	}

	public void setUsuarioOperacao(Cliente usuarioOperacao) {
		this.usuarioOperacao = usuarioOperacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}