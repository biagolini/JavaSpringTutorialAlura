package com.meuTutorial.forumDuvidas.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuTutorial.forumDuvidas.controller.dto.TopicoDto;
import com.meuTutorial.forumDuvidas.modelo.Curso;
import com.meuTutorial.forumDuvidas.modelo.Topico;

@RestController
public class TopicosController {
    @RequestMapping("/topicos")
    public List<TopicoDto> lista(){
        Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring", "Programa-ção"));        
        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}
