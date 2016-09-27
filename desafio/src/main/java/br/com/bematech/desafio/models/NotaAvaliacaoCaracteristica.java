package br.com.bematech.desafio.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bematech.desafio.enums.NotaAvaliacaoEnum;

@Entity
public class NotaAvaliacaoCaracteristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private CaracteristicaAvaliacao caracteristica;
	
	@Enumerated(EnumType.STRING)
	private NotaAvaliacaoEnum nota;
	
	@ManyToOne
	private Avaliacao avaliacao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CaracteristicaAvaliacao getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(CaracteristicaAvaliacao caracteristica) {
		this.caracteristica = caracteristica;
	}
	public NotaAvaliacaoEnum getNota() {
		return nota;
	}
	public void setNota(NotaAvaliacaoEnum nota) {
		this.nota = nota;
	}
	@JsonIgnore
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
}
