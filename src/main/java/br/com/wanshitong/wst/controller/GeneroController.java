package br.com.wanshitong.wst.controller;

import br.com.wanshitong.wst.entity.Genero;
import br.com.wanshitong.wst.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genero")
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Genero> getGeneroById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(generoService.findById(id));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<Genero> getGeneroByNome(@PathVariable(value = "nome") String nome){
        return ResponseEntity.ok(generoService.findByNome(nome));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Genero>> getAll(){
        return ResponseEntity.ok(generoService.findAll());
    }
}
