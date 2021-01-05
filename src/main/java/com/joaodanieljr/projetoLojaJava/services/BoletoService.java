package com.joaodanieljr.projetoLojaJava.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.joaodanieljr.projetoLojaJava.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	//simular data de vencimento do boleto (3 dias apos o registro do pedido)
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante){
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 3);
		pagto.setDataVencimento(cal.getTime());
	}

}
