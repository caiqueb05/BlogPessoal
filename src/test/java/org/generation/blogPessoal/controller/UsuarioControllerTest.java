package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;
    private Usuario usuarioUpdate;
    private Usuario usuarioAdmin;

    @BeforeAll
    public void start() {

        Usuario usuario = new Usuario(0L, "Administrador", "admin@mail.com", "1234567890");

        if(!usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
            HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
            testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        }

        usuario = new Usuario(0L, "Manoel Bezerra", "manoel.bezerra@mail.com", "1234567890");

        usuario = new Usuario(2L, "Paulo Antunes", "paulo.bezerra@mail.com", "1234567890");

    }

    @Test
    @DisplayName("Cadastrar Usuário!")
    @Order(1)
    public void deveRealizarPostUsuario() {

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

    }

    /*@Test
    @DisplayName("Listar todos os  Usuário!")
    @Order(2)
    public void deveRealizarGetAllUsuario() {

        ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin@mail.com", "1234567890").exchange("/usuarios/all", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }*/

    /*@Test
    @DisplayName("Alterar Usuário!")
    @Order(3)
    public void deveRealizarPutUsuario() {

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
        ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin@mail.com", "1234567890").exchange("/usuarios/alterar", HttpMethod.PUT, request, Usuario.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }*/

}
