package com.joaodanieljr.projetoLojaJava.services;

import org.springframework.mail.SimpleMailMessage;

import com.joaodanieljr.projetoLojaJava.domain.Cliente;
import com.joaodanieljr.projetoLojaJava.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
