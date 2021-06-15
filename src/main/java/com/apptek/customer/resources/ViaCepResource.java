package com.apptek.customer.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apptek.customer.model.Cep;

@RestController
@RequestMapping(value = "/viacep")
public class ViaCepResource {
	@RequestMapping(value = "/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Cep> find(@PathVariable String cep) {
		// Fonte: http://dfsistemasweb.com.br/utilizando-resttemplate-para-consumir-webservice-viacep/
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/{cep}/json/";
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("cep", cep);
	    
	    Cep obj = restTemplate.getForObject(uri, Cep.class, params);
		
		return ResponseEntity.ok().body(obj);
	}
}
