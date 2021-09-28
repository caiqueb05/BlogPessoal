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

    @Autowired
    private PostagemRepository repository;

    @GetMapping
    public ResponseEntity<List<Postagem>> GetAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> GetById(@PathVariable long id){
        return repository.findById(id)
                .stream().map(resp -> ResponseEntity.ok(resp))
                .findAny().orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Postagem> Post(@RequestBody Postagem novaPostagem){
        return ResponseEntity.status(201).body(repository.save(novaPostagem));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Postagem> Put(@RequestBody Postagem atualizarPostagem){
        return ResponseEntity.status(200).body(repository.save(atualizarPostagem));
    }

    @DeleteMapping("/deletar/{id}")
    public void DeleteById(@PathVariable long id){
        repository.deleteById(id);
    }

}
