package br.com.bematech.desafio.daos;

import br.com.bematech.desafio.models.Usuario;

public interface UsuarioDao {

	public Usuario findByUsername(String username);
	
	public void salvar(Usuario usuario);
}
