package com.apptek.customer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.apptek.customer.model.Cliente;
import com.apptek.customer.services.validation.ClienteUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, message = "O tamanho deve ser de no mínimo 06 caracteres")
	private String senha;
	
	private ClienteDTO usuarioOperacao;
	
	private Date dtOperacao;
		
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 telefone registrado.")
	@Size(min= 1, message="o cadastro do cliente deve ter no mínimo 01 telefone registrado.")
	@NotEmpty(message = "Preenchimento obrigatório")
	private List<TelefoneDTO> telefones = new ArrayList();
	
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 endereço registrado.")
	@Size(min= 1, message="o cadastro do cliente deve ter no mínimo 01 endereço registrado.")
	@NotEmpty(message = "Preenchimento obrigatório")
	private List<EnderecoDTO> enderecos = new ArrayList();	 

	@NotNull
	@NotEmpty(message="o cadastro do cliente deve ter no mínimo 01 email registrado.")
    @Size(min = 1, message="o cadastro do cliente deve ter no mínimo 01 email registrado.")
    @Valid
    private List<EmailDTO> emails = new ArrayList();

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente cliente) { // throws ParseException {
		if (cliente != null) {
			this.id = cliente.getId();
			this.cpf = cliente.getCpf();
			this.nome = cliente.getNome();
			this.usuario = cliente.getUsuario();
			/*
			this.usuarioOperacao = new ClienteDTO();
			this.usuarioOperacao.setCpf(cliente.getCpf());
			this.usuarioOperacao.setNome(cliente.getNome());
			*/
			this.enderecos = cliente.getEnderecos().stream().map(x -> new EnderecoDTO(x)).collect(Collectors.toList());
			this.telefones = cliente.getTelefones().stream().map(x -> new TelefoneDTO(x)).collect(Collectors.toList());
			this.emails    = cliente.getEmails().stream().map(x -> new EmailDTO(x)).collect(Collectors.toList());
			this.dtOperacao = cliente.getDtOperacao();
		}
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

	public ClienteDTO getUsuarioOperacao() {
		return usuarioOperacao;
	}

	public void setUsuarioOperacao(ClienteDTO usuarioOperacao) {
		this.usuarioOperacao = usuarioOperacao;
	}

	public List<TelefoneDTO> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneDTO> telefones) {
		this.telefones = telefones;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public List<EmailDTO> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailDTO> emails) {
		this.emails = emails;
	}

	public Date getDtOperacao() {
		return dtOperacao;
	}

	public void setDtOperacao(Date dtOperacao) {
		this.dtOperacao = dtOperacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}