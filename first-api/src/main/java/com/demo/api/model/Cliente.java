package com.demo.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Pattern(regexp = "[0-9]{11}", message = "CPF inválido") 
	@Column(nullable = false)
	private String CPF;
	
	@Column(nullable = false)
	private String email;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(nullable = false)
	private Date datanascimento;
	

}
