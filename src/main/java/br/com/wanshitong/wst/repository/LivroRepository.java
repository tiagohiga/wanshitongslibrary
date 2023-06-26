package br.com.wanshitong.wst.repository;

import br.com.wanshitong.wst.entity.Livro;
import br.com.wanshitong.wst.model.LivroConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Livro findByIsbn(String isbn);

    List<Livro> findAllByNomeLivroContainingIgnoreCase(String nome);

    List<Livro> findAllByIdAutorNomeAutorContainingIgnoreCase(String nomeAutor);

    List<Livro> findAllByIdEditoraNomeEditoraContainingIgnoreCase(String nomeEditora);

    List<Livro> findAllByIdGeneroNomeGeneroContainingIgnoreCase(String nomeGenero);

    @Query(value = "select lvr from Livro lvr where lvr.isbn in (:isbn)")
    Set<Livro> findAllByIsbnList(@Param("isbn") Set<String> isbn);
}
