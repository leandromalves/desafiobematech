package br.com.bematech.desafio.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.bematech.desafio.models.Usuario;
import br.com.bematech.desafio.services.UsuarioService;

@Controller
@Transactional
public class HomeController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/")
	public String index(Usuario usuario) {
		return "home/index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String adicionarUsuario(Usuario usuario) {
		usuarioService.salvar(usuario);
		return "home/sucesso";
	}
   
}
