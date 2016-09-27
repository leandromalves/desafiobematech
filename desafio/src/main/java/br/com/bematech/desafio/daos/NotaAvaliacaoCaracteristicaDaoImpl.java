package br.com.bematech.desafio.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bematech.desafio.models.Avaliacao;
import br.com.bematech.desafio.models.NotaAvaliacaoCaracteristica;

@Repository
public class NotaAvaliacaoCaracteristicaDaoImpl implements NotaAvaliacaoCaracteristicaDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void salvar(NotaAvaliacaoCaracteristica notaAvaliacaoCaracteristica) {
		manager.persist(notaAvaliacaoCaracteristica);
	}

	@Override
	public List<NotaAvaliacaoCaracteristica> listar() {
		return manager.createQuery("select n from NotaAvaliacaoCaracteristica n", 
				NotaAvaliacaoCaracteristica.class).getResultList();
	}

	@Override
	public NotaAvaliacaoCaracteristica consultar(Integer id) {
		return manager.find(NotaAvaliacaoCaracteristica.class, id);
	}

	@Override
	public void excluir(NotaAvaliacaoCaracteristica notaAvaliacaoCaracteristica) {
		manager.remove(notaAvaliacaoCaracteristica);
	}

	@Override
	public void alterar(NotaAvaliacaoCaracteristica notaAvaliacaoCaracteristica) {
		manager.merge(notaAvaliacaoCaracteristica);
	}

	@Override
	public List<NotaAvaliacaoCaracteristica> listarPorAvaliacao(Avaliacao avaliacao) {
		return manager.createQuery("select n from NotaAvaliacaoCaracteristica n where n.avaliacao.id= :avaliacaoId", 
				NotaAvaliacaoCaracteristica.class).setParameter("avaliacaoId", avaliacao.getId()).getResultList();
	}

	

}
