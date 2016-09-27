package br.com.bematech.desafio.services;

import java.util.List;

import br.com.bematech.desafio.models.Avaliacao;

public interface AvaliacaoService {
	
	void salvar(Avaliacao avaliacao);
	public List<Avaliacao> listar();
	public Avaliacao consultar(Integer id);
	public void excluir(Avaliacao avaliacao);
	public void alterar(Avaliacao avaliacao);
	
}
