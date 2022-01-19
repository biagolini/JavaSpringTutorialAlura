package com.meuTutorial.forumDuvidas.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.meuTutorial.forumDuvidas.modelo.Topico;

public class TopicoDto {
    // Atributos
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    
    // Construtor
    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();             
    }
    
    // Getters 
    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getMensagem() {
        return mensagem;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    // Metodo para retornar uma lista de topicos
    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }    
}
