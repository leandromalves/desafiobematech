package br.com.bematech.desafio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bematech.desafio.daos.AvaliacaoDao;
import br.com.bematech.desafio.daos.CaracteristicaAvaliacaoDao;
import br.com.bematech.desafio.daos.NotaAvaliacaoCaracteristicaDao;
import br.com.bematech.desafio.models.Avaliacao;
import br.com.bematech.desafio.models.NotaAvaliacaoCaracteristica;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	@Autowired
	private CaracteristicaAvaliacaoDao caracteristicaAvaliacaoDao;
	
	@Autowired
	private NotaAvaliacaoCaracteristicaDao notaAvaliacaoCaractDao;
	
	@Override
	public void salvar(Avaliacao avaliacao) {
		for (NotaAvaliacaoCaracteristica notaAvaliacaoCaracteristica : avaliacao.getNotasCaracteriscas()) {
			notaAvaliacaoCaracteristica.setAvaliacao(avaliacao);
			if(notaAvaliacaoCaracteristica.getCaracteristica().getId() == null) {
				caracteristicaAvaliacaoDao.salvar(notaAvaliacaoCaracteristica.getCaracteristica());
			}
		}
		avaliacaoDao.salvar(avaliacao);
	}

	@Override
	public List<Avaliacao> listar() {
		return avaliacaoDao.listar();
	}

	@Override
	public Avaliacao consultar(Integer id) {
		return avaliacaoDao.consultar(id);
	}

	@Override
	public void excluir(Avaliacao avaliacao) {
		avaliacaoDao.excluir(avaliacao);
	}

	@Override
	public void alterar(Avaliacao avaliacao) {
		for (NotaAvaliacaoCaracteristica notaAvalCaract : notaAvaliacaoCaractDao.listarPorAvaliacao(avaliacao)) {
			notaAvaliacaoCaractDao.excluir(notaAvalCaract);
		}
		
		for (NotaAvaliacaoCaracteristica notaAvaliacaoCaracteristica : avaliacao.getNotasCaracteriscas()) {
			notaAvaliacaoCaracteristica.setAvaliacao(avaliacao);
			if(notaAvaliacaoCaracteristica.getCaracteristica().getId() == null) {
				caracteristicaAvaliacaoDao.salvar(notaAvaliacaoCaracteristica.getCaracteristica());
			}
		}
		
		avaliacaoDao.alterar(avaliacao);
	}

}
