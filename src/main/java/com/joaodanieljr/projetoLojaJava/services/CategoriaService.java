package com.joaodanieljr.projetoLojaJava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaodanieljr.projetoLojaJava.domain.Categoria;
import com.joaodanieljr.projetoLojaJava.exceptions.ObjectNotFoundException;
import com.joaodanieljr.projetoLojaJava.repositories.CategoriaRepository;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
																"Objeto não encontrado! ID: " 
																+ id 
																+ ", Tipo: " 
																+ Categoria.class.getName())); 
		
		

	}
}
