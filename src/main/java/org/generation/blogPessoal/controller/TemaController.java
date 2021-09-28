package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {

    @Autowired
    private TemaRepository repository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> GetById(@PathVariable long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Tema>> GetByName(@PathVariable String nome){
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Tema> Post(@RequestBody Tema tema){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> Put(@RequestBody Tema tema){
        return ResponseEntity.ok(repository.save(tema));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        repository.deleteById(id);
    }


}
