package br.com.wanshitong.wst.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBWST_LVRS_EMPRST", schema = "WSTS")
public class LivroEmprestimo implements Serializable {
    @Id
    @Column(name = "ID_LVRO_EMPRST")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLvroEmprst;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESTIMO")
    private Emprestimo emprestimo;

    @ManyToOne
    @JoinColumn(name = "ID_LIVRO")
    private Livro livro;

    @Column(name = "RESP_BAIXA")
    private String responsavelBaixa;

    @Column(name = "DT_DEVOLUCAO")
    private LocalDate dataDevolucao;

    public LivroEmprestimo(Emprestimo emprestimo, Livro livro) {
        this.livro = livro;
        this.emprestimo = emprestimo;
    }

    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        if(object == null || object.getClass() != getClass()){
            return false;
        }

        final LivroEmprestimo other = LivroEmprestimo.class.cast(object);
        if(getIdLvroEmprst() == null && other.getIdLvroEmprst() == null){
            return getEmprestimo().equals(other.getEmprestimo()) &&
                    getLivro().equals(other.getLivro()) &&
                    getDataDevolucao().equals(other.getDataDevolucao()) &&
                    getResponsavelBaixa().equals(other.getResponsavelBaixa());

        }else{
            return getIdLvroEmprst().equals(other.getIdLvroEmprst());
        }
    }

    @Override
    public int hashCode(){
        final HashCodeBuilder hcb = new HashCodeBuilder(17, 37);
        if(idLvroEmprst == null){
            hcb.append(emprestimo);
            hcb.append(livro);
            hcb.append(dataDevolucao);
            hcb.append(responsavelBaixa);
        }else{
            hcb.append(idLvroEmprst);
        }

        return hcb.toHashCode();
    }
}
