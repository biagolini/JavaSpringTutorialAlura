package com.meuTutorial.forumDuvidas.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.meuTutorial.forumDuvidas.controller.dto.DetalhesDoTopicoDto;
import com.meuTutorial.forumDuvidas.controller.dto.TopicoDto;
import com.meuTutorial.forumDuvidas.controller.form.AtualizacaoTopicoForm;
import com.meuTutorial.forumDuvidas.controller.form.TopicoForm;
import com.meuTutorial.forumDuvidas.modelo.Topico;
import com.meuTutorial.forumDuvidas.repository.CursoRepository;
import com.meuTutorial.forumDuvidas.repository.TopicoRepository;

@RestController
@RequestMapping(value= "/topicos")
public class TopicosController {
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
		// Lista de todos os topicos
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}

	// Metodo para postar um topico
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody  @Valid  TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	// Metodo para obter dado de 1 topico especifico
	@GetMapping("/{id}")
    public DetalhesDoTopicoDto detalhar(@PathVariable Long id) {
       Topico topico = topicoRepository.getById(id);        
        return new DetalhesDoTopicoDto(topico);
    }
	
	// Metodo para atualizar dados
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
		Topico topico = form.atualizar(id, topicoRepository);
		return ResponseEntity.ok(new TopicoDto(topico));
	}

	// Metodo para excluir um registos
    @DeleteMapping("/{id}")
	@Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Registro apagado com sucesso");
    }

}
