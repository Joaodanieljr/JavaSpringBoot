package com.joaodanieljr.projetoLojaJava.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.joaodanieljr.projetoLojaJava.domain.enums.TipoCliente;
import com.joaodanieljr.projetoLojaJava.dto.ClienteNewDTO;
import com.joaodanieljr.projetoLojaJava.resources.exceptions.FieldMessage;
import com.joaodanieljr.projetoLojaJava.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
		}
		
		
		for(FieldMessage e: list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
