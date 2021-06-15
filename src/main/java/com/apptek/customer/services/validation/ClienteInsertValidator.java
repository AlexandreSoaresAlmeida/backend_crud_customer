package com.apptek.customer.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.apptek.customer.dto.ClienteNewDTO;
import com.apptek.customer.model.Cliente;
import com.apptek.customer.repository.ClienteRepository;
import com.apptek.customer.resources.exception.FieldMessage;
import com.apptek.customer.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (!BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}

		Cliente aux = clienteRepository.findByUsuario(objDto.getUsuario());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}