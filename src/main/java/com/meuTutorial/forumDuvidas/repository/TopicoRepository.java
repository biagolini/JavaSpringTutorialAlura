package com.meuTutorial.forumDuvidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meuTutorial.forumDuvidas.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

}
