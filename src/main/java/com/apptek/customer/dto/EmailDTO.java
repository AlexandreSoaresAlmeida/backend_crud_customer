package com.apptek.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.apptek.customer.services.validation.ClienteUpdate;

@ClienteUpdate
public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;

	// private Cliente usuarioOperacao;
	
	public EmailDTO() {
	}

	public EmailDTO(com.apptek.customer.model.Email email) {
		this.email = email.getEmail();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}