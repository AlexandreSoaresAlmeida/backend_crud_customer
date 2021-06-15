package com.apptek.customer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private Integer codigoUf;

	@Column(unique = true)
	private String descricao;
	
	@Column(unique = true)
	private String sigla;

	@JsonIgnore
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();

	public Estado() {

	}

	public Estado(Integer codigoUf, String descricao, String sigla) {
		super();
		this.codigoUf = codigoUf;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public Integer getCodigoUf() {
		return codigoUf;
	}

	public void setCodigoUf(Integer codigoUf) {
		this.codigoUf = codigoUf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoUf == null) ? 0 : codigoUf.hashCode());
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
		Estado other = (Estado) obj;
		if (codigoUf == null) {
			if (other.codigoUf != null)
				return false;
		} else if (!codigoUf.equals(other.codigoUf))
			return false;
		return true;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}