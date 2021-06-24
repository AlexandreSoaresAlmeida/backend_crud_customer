package com.apptek.customer.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Transactional
public class Endereco extends Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss")
	private Date dtOperacao;
	
	private Cliente userOperacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "aCidade_id")
	private Cidade cidade;

	public Endereco() {

	}

	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cliente cliente,
			Cidade cidade, Cliente userOperacao) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cliente = cliente;
		this.cidade = cidade;
		this.dtOperacao = new Date();
		this.userOperacao = userOperacao;
	}

	// Remove formatação do CEP
	@PrePersist
	@PreUpdate
	public void removerFormacatao() {
		this.setCep(this.cep.replaceAll("[^0-9]", ""));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCep() { //throws ParseException {
//		if (cep.length() > 0) {
//			cep = formatarString(cep, "##.###-###");
//		}
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String cidade = (getCidade() != null) ? getCidade().getNome() : "";
		String estado = ((getCidade() != null) && (getCidade().getEstado() != null))
				? getCidade().getEstado().getSigla()
				: "";
		builder.append("\n\n      | Logradouro: " + getLogradouro() + ", " + getComplemento() + " " + getNumero());
		builder.append("\n        | Bairro....: " + getBairro() + " CEP: " + getCep());
		builder.append("\n        | Cidade....: " + cidade + " - " + estado + "\n");
		return builder.toString();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}