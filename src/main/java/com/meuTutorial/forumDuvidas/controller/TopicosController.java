package com.meuTutorial.forumDuvidas.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.meuTutorial.forumDuvidas.controller.dto.DetalhesDoTopicoDto;
import com.meuTutorial.forumDuvidas.controller.dto.TopicoDto;
import com.meuTutorial.forumDuvidas.controller.form.AtualizacaoTopicoForm;
import com.meuTutorial.forumDuvidas.controller.form.TopicoForm;
import com.meuTutorial.forumDuvidas.modelo.Topico;
import com.meuTutorial.forumDuvidas.repository.CursoRepository;
import com.meuTutorial.forumDuvidas.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value= "/topicos")
public class TopicosController {
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	// Lista de todos os topicos com paginação
	@GetMapping
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int qtd) {
		
		Pageable paginacao = PageRequest.of(pagina, qtd);
		
		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
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
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
       Optional <Topico> topico = topicoRepository.findById(id);   // Metodo para em caso de o id não ser encontrado retornarmos o STATUS 404
	   if (topico.isPresent()) {     
		return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
		}
	   return ResponseEntity.notFound().build();
    }
	
	// Metodo para atualizar dados
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		
		return ResponseEntity.notFound().build();
	}


	// Metodo para excluir um registos
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Registro apagado com sucesso");
		}
		
		return ResponseEntity.notFound().build();
	}

}
