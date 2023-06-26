package br.com.wanshitong.wst.service;

import br.com.wanshitong.wst.entity.Emprestimo;
import br.com.wanshitong.wst.entity.Livro;
import br.com.wanshitong.wst.entity.LivroEmprestimo;
import br.com.wanshitong.wst.entity.Usuario;
import br.com.wanshitong.wst.enums.StatusEmprestimoEnum;
import br.com.wanshitong.wst.exception.BookBorrowFoundException;
import br.com.wanshitong.wst.exception.NoDataFoundException;
import br.com.wanshitong.wst.exception.NotEnoughStockException;
import br.com.wanshitong.wst.exception.RecordNotFoundException;
import br.com.wanshitong.wst.model.EmprestimoLivroModel;
import br.com.wanshitong.wst.model.EmprestimoModel;
import br.com.wanshitong.wst.model.EmprestimoResponseModel;
import br.com.wanshitong.wst.repository.EmprestimoRepository;
import br.com.wanshitong.wst.repository.LivroEmprestimoRepository;
import br.com.wanshitong.wst.repository.LivroRepository;
import br.com.wanshitong.wst.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmprestimoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroEmprestimoRepository livroEmprestimoRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Transactional
    public Emprestimo gerarEmprestimo(EmprestimoModel model) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmailUsuarioIgnoreCase(model.getEmail()))
                .orElseThrow(() -> new RecordNotFoundException("Usuario", model.getEmail()));

        Optional<Emprestimo> emprestimoAnterior = emprestimoRepository.findByEmailAndStatusAberto(
                usuario.get().getIdUsuario(),
                StatusEmprestimoEnum.ABERTO,
                StatusEmprestimoEnum.FORA_PRAZO);

        if(emprestimoAnterior.isPresent()) throw new BookBorrowFoundException(emprestimoAnterior.get().getIdEmprestimo().toString());

        Set<Livro> livros = livroRepository.findAllByIsbnList(model.getIsbnLivros());

        if(livros.isEmpty()) throw new NoDataFoundException("Livros");

        Emprestimo emprestimo = Emprestimo.builder()
                .usuario(usuario.get())
                .bibliotecario(model.getBibliotecario())
                .dataEmprestimo(LocalDate.now())
                .prazoDevolucao(LocalDate.now().plusDays(10))
                .build();

        Set<LivroEmprestimo> livroEmprestimos = new HashSet<>();

        if(!livros.isEmpty()){
            for(Livro l : livros){
                if(l.getQtdDisponivel() > 0) {
                    l.setQtdDisponivel(l.getQtdDisponivel() - 1);
                    LivroEmprestimo livroEmprestimo = new LivroEmprestimo();
                    livroEmprestimo.setEmprestimo(emprestimo);
                    livroEmprestimo.setLivro(l);
                    livroEmprestimos.add(livroEmprestimo);
                }
                else throw new NotEnoughStockException();
            }
        }

        emprestimo.setStatusEmprestimo(StatusEmprestimoEnum.ABERTO);

        emprestimo.setEmprestimos(livroEmprestimos);
        emprestimoRepository.save(emprestimo);

        return emprestimo;
    }

    public EmprestimoResponseModel baixarEmprestimo(EmprestimoModel model, Long id){
        System.out.println(model.getIsbnLivros().toString());
        Optional<Emprestimo> emprestimo = Optional.ofNullable(emprestimoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Emprestimo", id.toString())));

        Set<Livro> livros = livroRepository.findAllByIsbnList(model.getIsbnLivros());

        if(!livros.isEmpty()){
            for(Livro l : livros){
                validarBaixaLivro(l, model, emprestimo.get().getEmprestimos());
            }
        }

        validarStatusEmprestimo(emprestimo.get());

        emprestimoRepository.save(emprestimo.get());

        return EmprestimoResponseModel.builder()
                .id(emprestimo.get().getIdEmprestimo())
                .nomeUsuario(emprestimo.get().getUsuario().getNomeUsuario())
                .statusEmprestimo(emprestimo.get().getStatusEmprestimo())
                .prazoDevolucao(emprestimo.get().getPrazoDevolucao())
                .build();
    }

    private void validarStatusEmprestimo(Emprestimo emprestimo) {
        boolean isDevolvido = deveEncerrar(emprestimo.getEmprestimos());

        if(isDevolvido){
            if(LocalDate.now().isAfter(emprestimo.getPrazoDevolucao())){
                emprestimo.setStatusEmprestimo(StatusEmprestimoEnum.DEVOLUCAO_ATRASO);
            }else{
                emprestimo.setStatusEmprestimo(StatusEmprestimoEnum.DEVOLUCAO_PRAZO);
            }
        }
    }

    private boolean deveEncerrar(Set<LivroEmprestimo> emprestimos) {
        return emprestimos.stream().noneMatch(le -> Objects.isNull(le.getDataDevolucao()));
    }

    private void validarBaixaLivro(Livro l, EmprestimoModel model, Set<LivroEmprestimo> livroEmprestimos){
        for(LivroEmprestimo le : livroEmprestimos){
            if(l.getIdLivro() == le.getLivro().getIdLivro() && Objects.isNull(le.getDataDevolucao())){
                le.setDataDevolucao(LocalDate.now());
                le.setResponsavelBaixa(model.getBibliotecario());
                le.getLivro().setQtdDisponivel(le.getLivro().getQtdDisponivel() + 1);
                return;
            }
        }
    }

    public List<Emprestimo> findAll(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        if(emprestimos.isEmpty()) throw new NoDataFoundException("Emprestimo");
        return emprestimos;
    }

    public EmprestimoResponseModel findById(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Livro", id.toString()));

        Set<EmprestimoLivroModel> livrosModel = new HashSet<>();

        for(LivroEmprestimo e : emprestimo.getEmprestimos()){
            EmprestimoLivroModel model = new EmprestimoLivroModel();
            model.setIsbn(e.getLivro().getIsbn());
            model.setDataDevolucao(e.getDataDevolucao());
            livrosModel.add(model);
        }

        EmprestimoResponseModel model = EmprestimoResponseModel.builder()
                .id(emprestimo.getIdEmprestimo())
                .nomeUsuario(emprestimo.getUsuario().getEmailUsuario())
                .statusEmprestimo(emprestimo.getStatusEmprestimo())
                .dataEmprestimo(emprestimo.getDataEmprestimo())
                .prazoDevolucao(emprestimo.getPrazoDevolucao())
                .livros(livrosModel)
                .build();

        return model;
    }
}
