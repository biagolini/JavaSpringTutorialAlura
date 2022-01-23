package com.meuTutorial.forumDuvidas.config.security;

import java.util.Date;

import com.meuTutorial.forumDuvidas.modelo.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenService {

	// Chamamos a senha usada para criar os tokens
	@Value("${forum.jwt.secret}")
	private String secret;

	// Chamamos o dado de duração dos tokens
	@Value("${forum.jwt.expiration}")
	private String expiration;
	

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API do Fórum da Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}

