package org.generation.blogPessoal.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Busca lista de temas no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de temas"),
            @ApiResponse(code = 204, message = "Retorno sem tema")
    })
    @GetMapping
    public ResponseEntity<List<Tema>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @ApiOperation(value = "Busca tema por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna tema existente"),
            @ApiResponse(code = 204, message = "Retorno inexistente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tema> GetById(@PathVariable long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Busca tema por nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna tema existente"),
            @ApiResponse(code = 204, message = "Retorno inexistente")
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Tema>> GetByName(@PathVariable String nome){
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
    }

    @ApiOperation(value = "Salva novo tema no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna tema cadastrado")
    })
    @PostMapping
    public ResponseEntity<Tema> Post(@RequestBody Tema tema){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(tema));
    }

    @ApiOperation(value = "Atualizar tema existente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna tema atualizado")
    })
    @PutMapping
    public ResponseEntity<Tema> Put(@RequestBody Tema tema){
        return ResponseEntity.ok(repository.save(tema));
    }

    @ApiOperation(value = "Deletar tema existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Caso deletado!"),
            @ApiResponse(code = 400, message = "Id de tema invalido")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        repository.deleteById(id);
    }


}
