package com.apptek.customer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Telefone extends Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String codigoPais;
	private String ddd;
	private String numero;
	private String ramal;
	private Boolean contato;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss")
	private Date dtOperacao;
	
	private Cliente userOperacao;	

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="tipo_telefone_id")
	private TipoTelefone tipoTelefone;
	
	public Telefone() {
			
	}

	public Telefone(String codigoPais, String ddd, String numero, String ramal, 
			TipoTelefone tipoTelefone, Cliente cliente, Boolean contato, Cliente userOperacao) {
		super();
		this.codigoPais = codigoPais;
		this.ddd = ddd;
		this.numero = numero;
		this.ramal = ramal;
		this.tipoTelefone = tipoTelefone;
		this.cliente = cliente;
		this.contato = contato;
		this.dtOperacao = new Date();
		this.userOperacao = userOperacao;
	}

	// Remove formatação do Numero do telefone
	@PrePersist
	@PreUpdate
	public void removerFormacatao() {
		this.setNumero(this.numero.replaceAll("[^0-9]", ""));
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() { // throws ParseException {
//		if (numero.length() > 0) {
//			numero = formatarString(numero, "#####-####");
//		}		
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
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