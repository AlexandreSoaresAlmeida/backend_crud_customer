package com.apptek.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.model.Cliente;
import com.apptek.customer.services.validation.ClienteUpdate;

@ClienteUpdate
public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;

	private Cliente usuarioOperacao;
	
	public EmailDTO() {
	}

	public EmailDTO(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}