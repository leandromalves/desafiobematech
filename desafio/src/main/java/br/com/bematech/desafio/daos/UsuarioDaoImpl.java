package br.com.bematech.desafio.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bematech.desafio.models.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Usuario findByUsername(String username) {
		return manager.createQuery("select u from Usuario u where u.username = :username", Usuario.class)
				.setParameter("username", username)
				.getSingleResult();
	}

	@Override
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

}
