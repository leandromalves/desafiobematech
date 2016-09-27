package br.com.bematech.desafio.daos;

import java.util.List;

import br.com.bematech.desafio.models.Avaliacao;
import br.com.bematech.desafio.models.NotaAvaliacaoCaracteristica;

public interface NotaAvaliacaoCaracteristicaDao extends BaseDao<NotaAvaliacaoCaracteristica>{
	
	List<NotaAvaliacaoCaracteristica> listarPorAvaliacao(Avaliacao avaliacao);

}
