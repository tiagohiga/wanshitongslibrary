package br.com.wanshitong.wst.repository;

import br.com.wanshitong.wst.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByNomeGeneroContainingIgnoreCase(String nomeGenero);
}
