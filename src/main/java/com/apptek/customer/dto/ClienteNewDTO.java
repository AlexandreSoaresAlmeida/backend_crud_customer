package com.apptek.customer.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.apptek.customer.model.Cliente;
import com.apptek.customer.model.Email;
import com.apptek.customer.model.Endereco;
import com.apptek.customer.model.Telefone;
import com.apptek.customer.services.validation.ClienteInsert;

@ClienteInsert
@Transactional
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
	
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 telefone registrado.")
	@Size(min= 1, message="o cadastro do cliente deve ter no mínimo 01 telefone registrado.")
	@NotEmpty(message = "Preenchimento obrigatório")
	private ArrayList<Telefone> telefones;
	
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 endereço registrado.")
	@Size(min= 1, message="o cadastro do cliente deve ter no mínimo 01 endereço registrado.")
	@NotEmpty(message = "Preenchimento obrigatório")
	private ArrayList<Endereco> enderecos;

	@NotNull
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 email registrado.")
    @Size(min = 1, message="o cadastro do cliente deve ter no mínimo 01 email registrado.")
    @Valid
    private ArrayList<Email> emails;

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
	
	public ArrayList<Email> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
	
	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public ArrayList<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(ArrayList<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}