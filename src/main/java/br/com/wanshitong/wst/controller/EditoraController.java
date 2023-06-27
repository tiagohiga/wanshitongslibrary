package br.com.wanshitong.wst.controller;

import br.com.wanshitong.wst.entity.Editora;
import br.com.wanshitong.wst.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/editora")
public class EditoraController {
    @Autowired
    private EditoraService editoraService;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Editora> getEditoraById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(editoraService.findById(id));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<Editora> getEditoraByNome(@PathVariable(value = "nome") String nome){
        return ResponseEntity.ok(editoraService.findByNome(nome));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Editora>> getAll(){
        return ResponseEntity.ok(editoraService.findAll());
    }
}
