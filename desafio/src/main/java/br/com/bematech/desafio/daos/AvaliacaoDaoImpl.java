package br.com.bematech.desafio.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bematech.desafio.models.Avaliacao;

@Repository
public class AvaliacaoDaoImpl implements AvaliacaoDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void salvar(Avaliacao avaliacao) {
		manager.persist(avaliacao);
	}

	@Override
	public List<Avaliacao> listar() {
		return manager.createQuery("select a from Avaliacao a", Avaliacao.class).getResultList();
	}

	@Override
	public Avaliacao consultar(Integer id) {
		return manager.find(Avaliacao.class, id);
	}

	@Override
	public void excluir(Avaliacao avaliacao) {
		manager.remove(avaliacao);
	}

	@Override
	public void alterar(Avaliacao avaliacao) {
		manager.merge(avaliacao);
	}

	

}
