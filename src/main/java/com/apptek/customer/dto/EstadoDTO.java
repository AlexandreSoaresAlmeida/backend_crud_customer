package com.apptek.customer.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Cidade;
import com.apptek.customer.model.Estado;
import com.apptek.customer.services.validation.ClienteUpdate;

@ClienteUpdate
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer codigoUf;

	private String descricao;
	
	private String sigla;

	public EstadoDTO() {
	}

	public EstadoDTO(Estado estado) {
		this.codigoUf = estado.getCodigoUf();
		this.descricao = estado.getDescricao();
		this.sigla = estado.getSigla();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}