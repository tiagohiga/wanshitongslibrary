package br.com.wanshitong.wst.controller;

import br.com.wanshitong.wst.entity.Autor;
import br.com.wanshitong.wst.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(autorService.findById(id));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<Autor> getAutorByNome(@PathVariable(value = "nome") String nome){
        return ResponseEntity.ok(autorService.findByNome(nome));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Autor>> getAll(){
        return ResponseEntity.ok(autorService.findAll());
    }
}
