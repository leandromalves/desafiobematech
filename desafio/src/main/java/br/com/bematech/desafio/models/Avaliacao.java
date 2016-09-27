package br.com.bematech.desafio.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy="avaliacao", cascade=CascadeType.ALL)
	private List<NotaAvaliacaoCaracteristica> notasCaracteriscas;
	
	private String comentario;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<NotaAvaliacaoCaracteristica> getNotasCaracteriscas() {
		return notasCaracteriscas;
	}
	public void setNotasCaracteriscas(List<NotaAvaliacaoCaracteristica> notasCaracteriscas) {
		this.notasCaracteriscas = notasCaracteriscas;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

}
