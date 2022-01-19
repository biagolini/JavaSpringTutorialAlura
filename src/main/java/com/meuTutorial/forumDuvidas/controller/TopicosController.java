package com.meuTutorial.forumDuvidas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuTutorial.forumDuvidas.controller.dto.TopicoDto;
import com.meuTutorial.forumDuvidas.modelo.Topico;
import com.meuTutorial.forumDuvidas.repository.TopicoRepository;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista() {
        List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
    }
}
