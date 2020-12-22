package com.joaodanieljr.projetoLojaJava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaodanieljr.projetoLojaJava.domain.Pedido;
import com.joaodanieljr.projetoLojaJava.exceptions.ObjectNotFoundException;
import com.joaodanieljr.projetoLojaJava.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
																"Objeto não encontrado! ID: " 
																+ id 
																+ ", Tipo: " 
																+ Pedido.class.getName())); 
		
		

	}
}
