package br.com.bematech.desafio.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotaAvaliacaoEnum {
	
	OTIMO("Ótimo"),
	BOM("Bom"),
	REGULAR("Regular"),
	RUIM("Ruim"),
	MUITO_RUIM("Muito Ruim");
	
	private String nome;
	
	NotaAvaliacaoEnum(String nome){
		this.nome = nome;
	}
	
	@JsonValue
	public String getNome() {
		return nome;
	}

}
