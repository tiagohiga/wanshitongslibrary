package br.com.wanshitong.wst.controller;

import br.com.wanshitong.wst.entity.Emprestimo;
import br.com.wanshitong.wst.model.EmprestimoModel;
import br.com.wanshitong.wst.model.EmprestimoResponseModel;
import br.com.wanshitong.wst.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody EmprestimoModel model){
        return new ResponseEntity<Emprestimo>(emprestimoService.gerarEmprestimo(model), HttpStatus.CREATED);
    }

    @PutMapping("/baixar/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN')")
    public ResponseEntity<EmprestimoResponseModel> baixarEmprestimo(@PathVariable(name = "id") Long id, @RequestBody EmprestimoModel model){
        System.out.println("TESTE: " + model.getEmail().toString());
        return ResponseEntity.ok(emprestimoService.baixarEmprestimo(model, id));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAll(){
        return ResponseEntity.ok(emprestimoService.findAll());
    }

    @GetMapping(value = "/emprestimo/{id}")
    public ResponseEntity<EmprestimoResponseModel> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(emprestimoService.findById(id));
    }
}
