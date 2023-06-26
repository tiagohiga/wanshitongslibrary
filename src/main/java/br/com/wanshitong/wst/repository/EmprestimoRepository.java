package br.com.wanshitong.wst.repository;

import br.com.wanshitong.wst.entity.Emprestimo;
import br.com.wanshitong.wst.enums.StatusEmprestimoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(value = "select emp from Emprestimo emp where emp.usuario.idUsuario =:usuario and emp.statusEmprestimo in (:aberto, :foraPrazo)")
    Optional<Emprestimo> findByEmailAndStatusAberto(@Param("usuario") Long usuario,
                                                    @Param("aberto") StatusEmprestimoEnum aberto,
                                                    @Param("foraPrazo") StatusEmprestimoEnum foraPrazo);
}
