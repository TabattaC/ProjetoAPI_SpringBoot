package com.demo.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {		
	List<Cliente> findByNomeContains(String nome);
	
	List <Cliente> findByNomeAndCPFAndDatanascimentoBetween(Optional<String>nome, Optional <String> CPF, Date inicio, Date fim);
	
	List <Cliente> findByCPF(Optional<String>CPF);
	List <Cliente> findByNome(Optional<String>nome);


}
