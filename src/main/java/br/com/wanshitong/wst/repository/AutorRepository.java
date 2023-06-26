package br.com.wanshitong.wst.repository;

import br.com.wanshitong.wst.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeAutorContainingIgnoreCase(String nomeAutor);
}