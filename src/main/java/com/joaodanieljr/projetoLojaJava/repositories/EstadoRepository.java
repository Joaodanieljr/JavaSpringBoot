package com.joaodanieljr.projetoLojaJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaodanieljr.projetoLojaJava.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,	Integer> {

}
