package com.meuTutorial.forumDuvidas.repository;

import com.meuTutorial.forumDuvidas.modelo.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}
