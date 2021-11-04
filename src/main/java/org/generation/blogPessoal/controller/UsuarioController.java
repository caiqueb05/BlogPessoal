package org.generation.blogPessoal.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repositorio;

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Autentica usuario no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna credenciais de usuario"),
            @ApiResponse(code = 400, message = "Erro na requisição!")
    })
    @PostMapping("/logar")
    public ResponseEntity<UserLogin> Authentication(@RequestBody Optional<UserLogin> user) {
        return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @ApiOperation(value = "Salva novo usuario no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna usuario cadastrado"),
            @ApiResponse(code = 400, message = "Erro na requisição")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario usuario) {
        return usuarioService.CadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Email existente, cadastre outro email!.");
                });
    }

    @ApiOperation(value = "Busca lista de usuarios no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna com Usuarios"),
            @ApiResponse(code = 204, message = "Retorno sem Usuario")
    })
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> getAll(){
        if(repositorio.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        else {
            return ResponseEntity.status(200).body(repositorio.findAll());
        }
    }


}