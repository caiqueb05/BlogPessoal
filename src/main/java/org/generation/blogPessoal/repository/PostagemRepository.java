package org.generation.blogPessoal.repository;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    /**
     * Metodo utilizado para realizar pesquisa pela coluna titulo da tabela postagem
     * @param titulo
     * @return
     * @since 1.0
     * @author Caique
     */
    public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

    public List<Postagem> findById(long id);

}
