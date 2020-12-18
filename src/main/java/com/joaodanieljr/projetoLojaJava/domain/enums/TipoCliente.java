package com.joaodanieljr.projetoLojaJava.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoCliente c: TipoCliente.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id nao é valido");
	}
}
