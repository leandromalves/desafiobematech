package br.com.bematech.desafio.daos;

import java.util.List;

public interface BaseDao<T> {

	public void salvar(T obj);
	public List<T> listar();
	public T consultar(Integer id);
	public void excluir(T obj);
	public void alterar(T obj);
}
