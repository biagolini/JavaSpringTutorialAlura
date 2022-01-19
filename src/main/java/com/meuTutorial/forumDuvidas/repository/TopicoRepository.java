package com.meuTutorial.forumDuvidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meuTutorial.forumDuvidas.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	List<Topico> findByCursoNome(String nomeCurso);

	List<Topico> findByTitulo(String titulo);

	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPelaMinhaQueryPersonalizada(@Param("nomeCurso") String nomeCurso);



}
