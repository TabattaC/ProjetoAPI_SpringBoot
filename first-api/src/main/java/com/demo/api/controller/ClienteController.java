package com.demo.api.controller;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.demo.api.model.Cliente;
import com.demo.api.repository.ClienteRepository;
import com.demo.api.service.ClienteService;
import com.demo.api.service.CustomPage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/getClientes")
	public ResponseEntity<CustomPage<Cliente>> listar(Pageable pageable) {
        Page<Cliente> page = clienteService.listAllClientes(pageable);
        List<Cliente> content = page.getContent();
        CustomPage<Cliente> response = new CustomPage(content);

		return  ResponseEntity.ok(response);   
	}
	
	@PostMapping("/salvarCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("{id}")
	public Cliente getClienteById(@PathVariable Long id) {
		return clienteRepository.findById(id).get();
	}
	
	@DeleteMapping("{id}")
	public void deleteCliente(@PathVariable Long id) {
		 clienteRepository.deleteById(id);
	}
	
	
//	@GetMapping("/filter")
//	public List<Cliente>findClienteByName(@RequestParam("nome") String nome){
//		return clienteRepository.findByNomeContains(nome).stream()
//				.filter(cliente -> cliente.getNome().contains(nome)).toList();
//	}
//	
	@GetMapping("/filter")
	public ResponseEntity<?> findClientesByIntervaloDataNascimento(@RequestParam(required = false) Optional<String>nome,
			@RequestParam (required = false) Optional<String>CPF, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Optional<Date> inicio,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Optional<Date> fim){
			ResponseEntity<List<Cliente>> findCLientesList =  clienteService.buscarClientes(nome, CPF, inicio, fim);
			return ResponseEntity.ok(findCLientesList);
	}
	
	
}
