package br.com.bematech.desafio.services;

import br.com.bematech.desafio.models.Usuario;

public interface UsuarioService {

	public Usuario findByUsername(String username);
	
	public void salvar(Usuario usuario);
}
