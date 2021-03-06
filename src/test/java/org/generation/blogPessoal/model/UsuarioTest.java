package org.generation.blogPessoal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    public Usuario usuario;
    public Usuario usuarioNulo = new Usuario();

    @Autowired
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator = factory.getValidator();

    @BeforeEach
    public void start() {
        usuario = new Usuario(0L, "Caique Bezerra", "caiquebezerra", "caique.bezerra@mail.com", "1234567890");
    }

    @Test
    void testValidaAtributos() {
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);

        System.out.println(violacao.toString());

        assertTrue(violacao.isEmpty());

    }

    @Test
    void testNaoValidaAtributos() {
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);

        System.out.println(violacao.toString());

        assertFalse(violacao.isEmpty());

    }

}
