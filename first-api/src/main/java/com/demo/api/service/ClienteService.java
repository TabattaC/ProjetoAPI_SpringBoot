package com.demo.api.service;

import java.util.Date;
 import java.util.List;
 import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.api.model.Cliente;
import com.demo.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ResponseEntity<List<Cliente>> buscarClientes (Optional<String> nome, Optional<String> CPF, Optional<Date> inicio, Optional<Date> fim ){
		List<Cliente> resultClientes;
		Date dataInicio = new Date(0);
		Date dataFim = new Date();
        
		if(!inicio.isEmpty()) 
			dataInicio = inicio.get();
		
		if(!fim.isEmpty())
			dataFim = fim.get();	
		
		if(!CPF.isEmpty() && inicio.isEmpty() && fim.isEmpty() && nome.isEmpty()) {
			CPF.get();
			resultClientes = clienteRepository.findByCPF(CPF);
			return ResponseEntity.ok(resultClientes);
		}else if(CPF.isEmpty() && inicio.isEmpty() && fim.isEmpty() && !nome.isEmpty()) {
			nome.get();
			resultClientes = clienteRepository.findByNome(nome);
			return ResponseEntity.ok(resultClientes);
		}else {
			resultClientes = clienteRepository.findByNomeAndCPFAndDatanascimentoBetween(nome, CPF, dataInicio, dataFim);
			return ResponseEntity.ok(resultClientes);
		}
	}
	
	public Page <Cliente>  listAllClientes(Pageable pageable){
		return clienteRepository.findAll(pageable);
	}
	


}
