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

    @BeforeEach
    void start() {
        Usuario usuario = new Usuario("Caique", "Caique Bezerra", "caique.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            usuarioRepository.save(usuario);

        Usuario usuario1 = new Usuario("Manoel", "Manoel Bezerra", "manoel.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByEmail(usuario1.getEmail()).isPresent())
            usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario("Frederico", "Frederico Bezerra", "frederico.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByEmail(usuario2.getEmail()).isPresent())
            usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario("Paulo", "Paulo Antunes", "paulo.bezerra@mail.com", "1234567890");

        if(!usuarioRepository.findByEmail(usuario3.getEmail()).isPresent())
            usuarioRepository.save(usuario3);
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
