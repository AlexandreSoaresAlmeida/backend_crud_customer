package com.apptek.customer.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.apptek.customer.domains.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String usuario;

	@JsonIgnore
	private String senha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss")
	private Date dtOperacao;
	
	private Cliente userOperacao;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Email> emails = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<Integer>();

	public Cliente() {
		addPerfil(Perfil.COMUM);
	}

	public Cliente(String usuario, String nome, String cpf, String senha, Cliente userOperacao) {
		super();
		this.usuario = usuario;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.dtOperacao = new Date();
		this.userOperacao = userOperacao;
		addPerfil(Perfil.COMUM);
	}

	public Cliente(Long id, String usuario, String nome, String cpf, String senha, Cliente userOperacao) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.dtOperacao = new Date();
		this.userOperacao = userOperacao;
		addPerfil(Perfil.COMUM);
	}
	
	// Remove formatação do CPF
	@PrePersist
	@PreUpdate
	public void removerFormacatao() {
		this.setCpf(this.cpf.replaceAll("[^0-9]", ""));
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

	public String getCpf() throws ParseException {		
		if (cpf.length() > 0) {
			cpf = formatarString(cpf, "###.###.###-##"); 
		}
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Date getDtOperacao() {
		return dtOperacao;
	}

	public void setDtOperacao(Date dtOperacao) {
		this.dtOperacao = dtOperacao;
	}

	public void setPerfis(Set<Integer> perfis) {
		this.perfis = perfis;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Cliente getUserOperacao() {
		return userOperacao;
	}

	public void setUserOperacao(Cliente userOperacao) {
		this.userOperacao = userOperacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}