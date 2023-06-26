package br.com.wanshitong.wst.repository;

import br.com.wanshitong.wst.entity.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {
}
