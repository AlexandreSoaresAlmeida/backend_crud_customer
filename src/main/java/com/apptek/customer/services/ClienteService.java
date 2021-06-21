package com.apptek.customer.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apptek.customer.domains.enums.Perfil;
import com.apptek.customer.dto.ClienteDTO;
import com.apptek.customer.dto.ClienteNewDTO;
import com.apptek.customer.model.Cliente;
import com.apptek.customer.model.Endereco;
import com.apptek.customer.repository.ClienteRepository;
import com.apptek.customer.repository.EnderecoRepository;
import com.apptek.customer.resources.exception.ObjectNotFoundException;
import com.apptek.customer.security.UserSS;
import com.apptek.customer.services.exceptions.AuthorizationException;
import com.apptek.customer.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;


	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public Cliente find(Long id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId().longValue())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) throws ParseException {
		Cliente newObj = find(obj.getId().longValue());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findByUsuario(String usuario) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !usuario.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado");
		}

		Cliente obj = clienteRepository.findByUsuario(usuario);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		// Cliente(Integer id, String usuario, String nome, String cpf, String senha, Cliente userOperacao)
		return new Cliente(objDto.getId(), objDto.getUsuario(), objDto.getNome(), 
				objDto.getCpf(), 
				pe.encode(objDto.getSenha()),
				objDto.getUsuarioOperacao());
	}

	@Transactional
	public Cliente fromDTO(ClienteNewDTO objDto) throws ParseException {
		// Cliente(Integer id, String usuario, String nome, String cpf, String senha, Cliente userOperacao)
		Cliente cli = new Cliente(
							objDto.getUsuario(), 
							objDto.getNome(),
							objDto.getCpf(), 
							pe.encode(objDto.getSenha()),
							objDto.getUsuarioOperacao());
		
		for (Endereco e : objDto.getEnderecos()) {
			Endereco end = new Endereco(
								e.getLogradouro(), 
								e.getNumero(), 
								e.getComplemento(), 
								e.getBairro(), 
								e.getCep(), 
								e.getCliente(),
								e.getCidade(), 
								e.getUserOperacao());
			cli.getEnderecos().addAll(Arrays.asList(end));
		}
		cli.setTelefones(objDto.getTelefones());		
		cli.setEmails(objDto.getEmails());
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) throws ParseException {
		newObj.setNome(obj.getNome());
		newObj.setUsuario(obj.getUsuario());
		newObj.setCpf(obj.getCpf());
		newObj.setEmails(obj.getEmails());
	}
}