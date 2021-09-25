package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

    Postagem postagem = new Postagem();

    @Autowired
    private PostagemRepository repository;

    @GetMapping
    public ResponseEntity<List<Postagem>> GetAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Postagem>> GetById1(){
        long id = postagem.getId();
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping("/1")
    public ResponseEntity<List<Postagem>> GetById(){
        return ResponseEntity.ok(repository.findById(1));
    }

}
