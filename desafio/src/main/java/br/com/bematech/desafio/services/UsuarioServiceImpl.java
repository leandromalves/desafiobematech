package br.com.bematech.desafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bematech.desafio.daos.UsuarioDao;
import br.com.bematech.desafio.models.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);
	}

	
}
