package br.com.wanshitong.wst.service;

import br.com.wanshitong.wst.entity.Autor;
import br.com.wanshitong.wst.entity.Editora;
import br.com.wanshitong.wst.entity.Genero;
import br.com.wanshitong.wst.entity.Livro;
import br.com.wanshitong.wst.exception.DuplicateRecordException;
import br.com.wanshitong.wst.exception.NoDataFoundException;
import br.com.wanshitong.wst.exception.RecordNotFoundException;
import br.com.wanshitong.wst.model.LivroAtualizacaoModel;
import br.com.wanshitong.wst.model.LivroCadastroModel;
import br.com.wanshitong.wst.repository.AutorRepository;
import br.com.wanshitong.wst.repository.EditoraRepository;
import br.com.wanshitong.wst.repository.GeneroRepository;
import br.com.wanshitong.wst.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro atualizarLivro(String isbn, LivroAtualizacaoModel model){
        System.out.println("MODEL RECEBIDO: " + model.toString());
        Livro livroExistente = Optional.ofNullable(livroRepository.findByIsbn(isbn))
                .orElseThrow(() -> new RecordNotFoundException("Livro", isbn));

        livroExistente.setImgLivro(model.getImgLivro());
        livroExistente.setNomeLivro(model.getNomeLivro());
        livroExistente.setDescricaoLivro(model.getDescricaoLivro());
        livroExistente.setQtdDisponivel(model.getQtdDisponivel().intValue());

        return livroRepository.saveAndFlush(livroExistente);
    }

    public Livro cadastrarLivro(LivroCadastroModel model) {
        Optional<Livro> livroExistente = Optional.ofNullable(livroRepository.findByIsbn(model.getIsbn()));

        if(livroExistente.isPresent()) throw new DuplicateRecordException(model.getIsbn());

        Editora editora = editoraRepository.findByNomeEditoraContainingIgnoreCase(model.getIdEditora())
                .orElseThrow(() -> new RecordNotFoundException("Editora", model.getIdEditora()));

        Genero genero = generoRepository.findByNomeGeneroContainingIgnoreCase(model.getIdGenero())
                .orElseThrow(() -> new RecordNotFoundException("Genero", model.getIdGenero()));

        Autor autor = autorRepository.findByNomeAutorContainingIgnoreCase(model.getIdAutor())
                .orElseThrow(() -> new RecordNotFoundException("Autor", model.getIdAutor()));

        Livro livroCadastro = Livro.builder()
                .isbn(model.getIsbn())
                .imgLivro(model.getImgLivro())
                .nomeLivro(model.getNomeLivro())
                .descricaoLivro(model.getDescricaoLivro())
                .idAutor(autor)
                .idEditora(editora)
                .idGenero(genero)
                .qtdDisponivel(model.getQtdDisponivel().intValue())
                .bibliotecario(model.getMatriculaBibliotecario())
                .build();

        livroRepository.save(livroCadastro);

        return livroCadastro;
    }

    public List<Livro> findAllByNomeEditora(String nomeEditora){
        List<Livro> livros = livroRepository.findAllByIdEditoraNomeEditoraContainingIgnoreCase(nomeEditora);
        if(livros.isEmpty()) throw new NoDataFoundException("Editora");
        return livros;
    }

    public List<Livro> findAllByNomeAutor(String nomeAutor){
        List<Livro> livros = livroRepository.findAllByIdAutorNomeAutorContainingIgnoreCase(nomeAutor);
        if(livros.isEmpty()) throw new NoDataFoundException("Autor");
        return livros;
    }

    public List<Livro> findAllByNomeGenero(String nomeGenero){
        List<Livro> livros = livroRepository.findAllByIdGeneroNomeGeneroContainingIgnoreCase(nomeGenero);
        if(livros.isEmpty()) throw new NoDataFoundException("Genero");
        return livros;
    }

    public Livro findByIsbn(String isbn){
        return Optional.ofNullable(livroRepository.findByIsbn(isbn))
                .orElseThrow(() -> new RecordNotFoundException("Livro", isbn));
    }

    public List<Livro> findByNome(String nome){
        List<Livro> livros = livroRepository.findAllByNomeLivroContainingIgnoreCase(nome);
        if(livros.isEmpty()) throw new NoDataFoundException("Livro");
        return livros;
    }

    public List<Livro> findAll() {
        List<Livro> livros = livroRepository.findAll();
        if(livros.isEmpty()) throw new NoDataFoundException("Livro");
        return livros;
    }
}
