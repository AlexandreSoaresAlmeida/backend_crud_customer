package com.apptek.customer.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Telefone;
import com.apptek.customer.model.TipoTelefone;
import com.apptek.customer.services.validation.ClienteUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@ClienteUpdate
public class TipoTelefoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Boolean ativado;

	private String descricao;
	
	private Integer qtdNumeros;
	
	public TipoTelefoneDTO() {
	}

	public TipoTelefoneDTO(TipoTelefone tipoTelefone) {
		this.ativado = tipoTelefone.getAtivado();
		this.descricao = tipoTelefone.getDescricao();
		this.qtdNumeros= tipoTelefone.getQtdNumeros();
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAtivado() {
		return ativado;
	}

	public void setAtivado(Boolean ativado) {
		this.ativado = ativado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdNumeros() {
		return qtdNumeros;
	}

	public void setQtdNumeros(Integer qtdNumeros) {
		this.qtdNumeros = qtdNumeros;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}