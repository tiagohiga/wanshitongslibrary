package br.com.wanshitong.wst.repository;

import br.com.wanshitong.wst.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    Optional<Editora> findByNomeEditoraContainingIgnoreCase(String nomeEditora);
}
