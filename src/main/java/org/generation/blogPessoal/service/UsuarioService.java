package org.generation.blogPessoal.service;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Optional<Object> CadastrarUsuario(Usuario usuarioParaCadastrar) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return repository.findByEmail(usuarioParaCadastrar.getEmail()).map(usuarioExistente -> {
            return Optional.empty();
        }).orElseGet(() -> {
            usuarioParaCadastrar.setSenha(encoder.encode(usuarioParaCadastrar.getSenha()));
            return Optional.ofNullable(repository.save(usuarioParaCadastrar));
        });

    }

    public Optional<UserLogin> Logar(Optional<UserLogin> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());

        if(usuario.isPresent()){
            if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
                String auth = user.get().getEmail() + ":" + user.get().getSenha();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(authHeader);
                user.get().setId(usuario.get().getId());
                user.get().setNome(usuario.get().getNome());
                user.get().setFoto(usuario.get().getFoto());
                user.get().setTipo(usuario.get().getTipo());

                return user;
            }
        }
        return null;
    }

}
