package org.generation.blogPessoal.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Busca todas as postagens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna postagem existente"),
            @ApiResponse(code = 204, message = "Retorno inexistente")
    })
    @GetMapping
    public ResponseEntity<List<Postagem>> GetAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @ApiOperation(value = "Busca postagem por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna postagem existente"),
            @ApiResponse(code = 204, message = "Retorno inexistente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> GetById(@PathVariable long id){
        return repository.findById(id)
                .stream().map(resp -> ResponseEntity.ok(resp))
                .findAny().orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Busca postagem por Título")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna postagem existente"),
            @ApiResponse(code = 204, message = "Retorno inexistente")
    })
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @ApiOperation(value = "Salva nova postagem no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna postagem cadastrada")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Postagem> Post(@RequestBody Postagem novaPostagem){
        return ResponseEntity.status(201).body(repository.save(novaPostagem));
    }

    @ApiOperation(value = "Atualizar postagem existente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna postagem atualizada")
    })
    @PutMapping("/atualizar")
    public ResponseEntity<Postagem> Put(@RequestBody Postagem atualizarPostagem){
        return ResponseEntity.status(200).body(repository.save(atualizarPostagem));
    }

    @ApiOperation(value = "Deletar postagem existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Caso deletada!"),
            @ApiResponse(code = 400, message = "Id de postagem invalida")
    })
    @DeleteMapping("/deletar/{id}")
    public void DeleteById(@PathVariable long id){
        repository.deleteById(id);
    }

}
