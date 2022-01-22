package com.meuTutorial.forumDuvidas.repository;


import java.util.Optional;

import com.meuTutorial.forumDuvidas.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}