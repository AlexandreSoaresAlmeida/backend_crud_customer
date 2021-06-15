package com.apptek.customer.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apptek.customer.domains.enums.Perfil;
import com.apptek.customer.model.Cidade;
import com.apptek.customer.model.Cliente;
import com.apptek.customer.model.Email;
import com.apptek.customer.model.Endereco;
import com.apptek.customer.model.Estado;
import com.apptek.customer.model.Telefone;
import com.apptek.customer.model.TipoTelefone;
import com.apptek.customer.repository.CidadeRepository;
import com.apptek.customer.repository.ClienteRepository;
import com.apptek.customer.repository.EstadoRepository;
import com.apptek.customer.repository.TipoTelefoneRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private TipoTelefoneRepository tipoTelefoneRepository;
	
	public void instantiateDevDatabase() throws ParseException {
		instantiateTestAppTekCrmDatabase();
	}

	public void instantiateTestAppTekCrmDatabase() throws ParseException {

		// Cadastro de Clientes
		Cliente cliente = new Cliente("edu", "Eduardo", "752.145.855-94", pe.encode("123456"), null);
		cliente.addPerfil(Perfil.COMUM);
		cliente.addPerfil(Perfil.ADMIN);
		
		// Cadastro de tipos de telefones
		TipoTelefone tipoTel1 = new TipoTelefone("Residencial", 8, true);
		TipoTelefone tipoTel2 = new TipoTelefone("Comercial", 8, true);
		TipoTelefone tipoTel3 = new TipoTelefone("Celular", 9, true);		
		tipoTelefoneRepository.save(tipoTel1);
		tipoTelefoneRepository.save(tipoTel2);
		tipoTelefoneRepository.save(tipoTel3);
		
		// Cadastro do domínio de estados
		Estado est11 = new Estado(11,"Rondônia","RO");
		Estado est12 = new Estado(12,"Acre","AC");
		Estado est13 = new Estado(13,"Amazonas","AM");
		Estado est14 = new Estado(14,"Roraima","RR");
		Estado est15 = new Estado(15,"Pará","PA");
		Estado est16 = new Estado(16,"Amapá","AP");
		Estado est17 = new Estado(17,"Tocantins","TO");
		Estado est21 = new Estado(21,"Maranhão","MA");
		Estado est22 = new Estado(22,"Piauí","PI");
		Estado est23 = new Estado(23,"Ceará","CE");
		Estado est24 = new Estado(24,"Rio Grande do Norte","RN");
		Estado est25 = new Estado(25,"Paraíba","PB");
		Estado est26 = new Estado(26,"Pernambuco","PE");
		Estado est27 = new Estado(27,"Alagoas","AL");
		Estado est28 = new Estado(28,"Sergipe","SE");
		Estado est29 = new Estado(29,"Bahia","BA");
		Estado est31 = new Estado(31,"Minas Gerais","MG");
		Estado est32 = new Estado(32,"Espírito Santo","ES");
		Estado est33 = new Estado(33,"Rio de Janeiro","RJ");
		Estado est35 = new Estado(35,"São Paulo","SP");
		Estado est41 = new Estado(41,"Paraná","PR");
		Estado est42 = new Estado(42,"Santa Catarina","SC");
		Estado est43 = new Estado(43,"Rio Grande do Sul","RS");
		Estado est50 = new Estado(50,"Mato Grosso do Sul","MS");
		Estado est51 = new Estado(51,"Mato Grosso","MT");
		Estado est52 = new Estado(52,"Goiás","GO");
		Estado est53 = new Estado(53,"Distrito Federal","DF");
		estadoRepository.save(est11);
		estadoRepository.save(est11);
		estadoRepository.save(est12);
		estadoRepository.save(est13);
		estadoRepository.save(est14);
		estadoRepository.save(est15);
		estadoRepository.save(est16);
		estadoRepository.save(est17);
		estadoRepository.save(est21);
		estadoRepository.save(est22);
		estadoRepository.save(est23);
		estadoRepository.save(est24);
		estadoRepository.save(est25);
		estadoRepository.save(est26);
		estadoRepository.save(est27);
		estadoRepository.save(est28);
		estadoRepository.save(est29);
		estadoRepository.save(est31);
		estadoRepository.save(est32);
		estadoRepository.save(est33);
		estadoRepository.save(est35);
		estadoRepository.save(est41);
		estadoRepository.save(est42);
		estadoRepository.save(est43);
		estadoRepository.save(est50);
		estadoRepository.save(est51);
		estadoRepository.save(est52);
		estadoRepository.save(est53);
		estadoRepository.save(est11);

		// Cadastro de algumas cidades
		Cidade cid1 = new Cidade("Brasília", est53);
		Cidade cid2 = new Cidade("São Paulo", est35);
		Cidade cid3 = new Cidade("Guarulhos", est35);
		cidadeRepository.save(cid1);
		cidadeRepository.save(cid2);
		cidadeRepository.save(cid3);
		
		// Cadastro de endereços
		Endereco enderecoCliente = new Endereco("Quadra SQSW", "303", "Bloco B, Apto 503", "Sudoeste", "70.673-302", cliente, cid1, null);
		Endereco enderecoCobranca = new Endereco("QE 28 conjunto D", "26", "casa", "Guará II", "71.060-042", cliente, cid1, null);

		cliente.getEnderecos().addAll(Arrays.asList(enderecoCliente, enderecoCobranca));
		
		// Cadastro de telefones
		Telefone tel1 = new Telefone("+55","61","9 9999-9999","",tipoTel3, cliente, true, null);     // celular
		Telefone tel2 = new Telefone("+55","11","3999-5999","145",tipoTel2, cliente, false, null); // comercial
		Telefone tel3 = new Telefone("+55","61","3381-9995","",tipoTel1, cliente, false, null);    // residencial
		cliente.getTelefones().addAll(Arrays.asList(tel1, tel2, tel3));

		// Email
		// String email, Boolean contato, Cliente cliente, Boolean ativado, Cliente userOperacao
		Email email1 = new Email("eduardo@gmail.com", true, cliente, true, null);
		Email email2 = new Email("eduardo@hotmail.com", false, cliente, true, null);
		cliente.getEmails().addAll(Arrays.asList(email1, email2));		
		
		clienteRepository.saveAll(Arrays.asList(cliente));
	}	
}