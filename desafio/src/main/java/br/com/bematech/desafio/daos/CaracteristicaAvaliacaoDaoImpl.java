package br.com.bematech.desafio.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bematech.desafio.models.CaracteristicaAvaliacao;

@Repository
public class CaracteristicaAvaliacaoDaoImpl implements CaracteristicaAvaliacaoDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void salvar(CaracteristicaAvaliacao caracteristicaAvaliacao) {
		manager.persist(caracteristicaAvaliacao);
	}

	@Override
	public List<CaracteristicaAvaliacao> listar() {
		return manager.createQuery("select c from CaracteristicaAvaliacao c", CaracteristicaAvaliacao.class).getResultList();
	}

	@Override
	public CaracteristicaAvaliacao consultar(Integer id) {
		return manager.find(CaracteristicaAvaliacao.class, id);
	}

	@Override
	public void excluir(CaracteristicaAvaliacao caracteristicaAvaliacao) {
		manager.remove(caracteristicaAvaliacao);
	}

	@Override
	public void alterar(CaracteristicaAvaliacao caracteristicaAvaliacao) {
		manager.merge(caracteristicaAvaliacao);
	}

	

}
