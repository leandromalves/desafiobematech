package br.com.bematech.desafio.controllers;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bematech.desafio.models.Avaliacao;
import br.com.bematech.desafio.models.Usuario;
import br.com.bematech.desafio.services.AvaliacaoService;
import br.com.bematech.desafio.services.UsuarioService;

@RestController
@Transactional
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private static final Logger LOG = Logger.getLogger(AvaliacaoController.class);
	
	@RequestMapping(value="/avaliacao/", method = RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listar() {
		LOG.info("Avaliacao.Listar: inicio do método");
		
		List<Avaliacao> avaliacaoList = avaliacaoService.listar();
		
		if(avaliacaoList.isEmpty()) {
			LOG.info("Avaliacao.Listar: retorno vazio");
			return new ResponseEntity<List<Avaliacao>>(HttpStatus.NO_CONTENT);
		}
		
		LOG.info("Avaliacao.Listar: tamanho do retorno: " + avaliacaoList.size());
		return new ResponseEntity<List<Avaliacao>>(avaliacaoList, HttpStatus.OK);
	}

	@RequestMapping(value="/avaliacao/{id}", method=RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscar(@PathVariable("id") int id) {
		Avaliacao avaliacao = avaliacaoService.consultar(id);
		
		if(avaliacao == null) {
			return new ResponseEntity<Avaliacao>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Avaliacao>(avaliacao, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/avaliacao/", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionar(@RequestBody Avaliacao avaliacao, UriComponentsBuilder ucBuilder,
    		Principal principal) {
 
		if(avaliacao.getNotasCaracteriscas() == null || avaliacao.getNotasCaracteriscas().isEmpty()) {
			LOG.error("Campo Notas nulo ou vázio");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuarioLogado = usuarioService.findByUsername(principal.getName());
		avaliacao.setUsuario(usuarioLogado);
		
		avaliacaoService.salvar(avaliacao);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/avaliacao/{id}").buildAndExpand(avaliacao.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/avaliacao/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Avaliacao> atualizar(@PathVariable("id") int id, @RequestBody Avaliacao avaliacao) {
		
		if(avaliacao.getNotasCaracteriscas() == null || avaliacao.getNotasCaracteriscas().isEmpty()) {
			LOG.error("Campo Notas nulo ou vázio");
			return new ResponseEntity<Avaliacao>(HttpStatus.BAD_REQUEST);
		}
         
        Avaliacao currentAvaliacao = avaliacaoService.consultar(id);
         
        if (currentAvaliacao == null) {
            return new ResponseEntity<Avaliacao>(HttpStatus.NOT_FOUND);
        }
 
        currentAvaliacao.setComentario(avaliacao.getComentario());
        currentAvaliacao.setNotasCaracteriscas(avaliacao.getNotasCaracteriscas());
        
        avaliacaoService.alterar(currentAvaliacao);
        
        return new ResponseEntity<Avaliacao>(currentAvaliacao, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/avaliacao/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Avaliacao> delete(@PathVariable("id") int id) {
		Avaliacao avaliacao = avaliacaoService.consultar(id);
		if (avaliacao == null) {
			return new ResponseEntity<Avaliacao>(HttpStatus.NOT_FOUND);
        }
		 
		avaliacaoService.excluir(avaliacao);
		return new ResponseEntity<Avaliacao>(HttpStatus.NO_CONTENT);
	 }
	
}
