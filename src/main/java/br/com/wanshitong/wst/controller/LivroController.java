package br.com.wanshitong.wst.controller;

import br.com.wanshitong.wst.entity.Livro;
import br.com.wanshitong.wst.model.LivroAtualizacaoModel;
import br.com.wanshitong.wst.model.LivroCadastroModel;
import br.com.wanshitong.wst.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/livro")
@Validated
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PutMapping(value = "/edit/{isbn}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable(value = "isbn") String isbn, @RequestBody @Valid LivroAtualizacaoModel model){
        return ResponseEntity.ok(livroService.atualizarLivro(isbn, model));
    }

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody @jakarta.validation.Valid LivroCadastroModel model){
        return new ResponseEntity<Livro>(livroService.cadastrarLivro(model), HttpStatus.CREATED);
    }

    @GetMapping(value = "/genero/all")
    public ResponseEntity<List<Livro>> getAllLivrosByGenero(@RequestParam("nome") String nome){
        return ResponseEntity.ok(livroService.findAllByNomeGenero(nome));
    }

    @GetMapping(value = "/autor/all")
    public ResponseEntity<List<Livro>> getAllLivrosByAutor(@RequestParam("nome") String nome){
        return ResponseEntity.ok(livroService.findAllByNomeAutor(nome));
    }

    @GetMapping(value = "/editora/all")
    public ResponseEntity<List<Livro>> getAllLivrosByEditora(@RequestParam("nome") String nome){
        return ResponseEntity.ok(livroService.findAllByNomeEditora(nome));
    }

    @GetMapping(value = "/isbn/{isbn}")
    public ResponseEntity<Livro> getByIsbn(@PathVariable(value = "isbn") String isbn){
        return ResponseEntity.ok(livroService.findByIsbn(isbn));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<Livro>> getAllByNome(@PathVariable(value = "nome") String nome){
        return ResponseEntity.ok(livroService.findAllByNomeAutor(nome));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> getAll(){
        return ResponseEntity.ok(livroService.findAll());
    }
}
