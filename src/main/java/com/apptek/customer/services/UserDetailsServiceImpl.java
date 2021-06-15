package com.apptek.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apptek.customer.model.Cliente;
import com.apptek.customer.repository.ClienteRepository;
import com.apptek.customer.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Cliente cli = repo.findByUsuario(usuario);
		if (cli == null) {
			throw new UsernameNotFoundException(usuario);
		}
		return new UserSS(cli.getId(), cli.getUsuario(), cli.getSenha(), cli.getPerfis());
	}

}
