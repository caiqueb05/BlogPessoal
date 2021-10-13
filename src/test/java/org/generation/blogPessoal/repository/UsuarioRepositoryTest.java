package org.generation.blogPessoal.repository;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start() {
        Usuario usuario = new Usuario(0L, "Caique Bezerra", "caique.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            usuarioRepository.save(usuario);

        usuario = new Usuario(0L, "Manoel Bezerra", "manoel.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            usuarioRepository.save(usuario);

        usuario = new Usuario(0L, "Frederico Bezerra", "frederico.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            usuarioRepository.save(usuario);

        usuario = new Usuario(0L, "Paulo Antunes", "paulo.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            usuarioRepository.save(usuario);
    }

    @Test
    @DisplayName("Retorna Nome")
    public void findByRetornaNome() {
        Usuario usuario = usuarioRepository.findByNome("Manoel Bezerra");
        assertTrue(usuario.getNome().equals("Manoel Bezerra"));
    }

    @Test
    @DisplayName("Retorna 3 Usuários")
    public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Bezerra");
        assertEquals(3, listaDeUsuarios.size());
    }

    @AfterAll
    public void end() {
        System.out.println("Teste Finalizado!");
    }

}
