package com.apptek.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.apptek.customer.model.Cliente;
import com.apptek.customer.model.Telefone;
import com.apptek.customer.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 100, message = "O tamanho deve estar entre 3 e 100 caracteres")
	@Pattern(regexp = "[a-zA-Z0-9]*\s") // Permite apenas letras, espaços e números 
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve estar entre 5 e 120 caracteres")
	private String usuario;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 11, max = 11, message = "O tamanho deve ser de 11")
	@CPF
	private String cpf;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, message = "O tamanho deve ser de no mínimo 06 caracteres")
	private String senha;
	
	private Cliente usuarioOperacao;
		
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 telefone registrado.")
	@Size(min= 1, message="o cadastro do cliente deve ter no mínimo 01 telefone registrado.")
	private Telefone telefones;
	
	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.usuario = obj.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return this.cpf;
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

	public Cliente getUsuarioOperacao() {
		return usuarioOperacao;
	}

	public void setUsuarioOperacao(Cliente usuarioOperacao) {
		this.usuarioOperacao = usuarioOperacao;
	}

	public Telefone getTelefones() {
		return telefones;
	}

	public void setTelefones(Telefone telefones) {
		this.telefones = telefones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}