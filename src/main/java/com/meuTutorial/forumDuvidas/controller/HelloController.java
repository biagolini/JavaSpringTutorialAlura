package com.meuTutorial.forumDuvidas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @RequestMapping("/")
    @ResponseBody
    public String hello() {
      return "Bem vindo ao serviço de back end de duvidas! Veja todas as duvidas em http://localhost:8080/topicos, e veja uma duvida em especifico usando http://localhost:8080/topicos/id.";
    }

}
