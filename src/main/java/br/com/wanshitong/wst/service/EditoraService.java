package br.com.wanshitong.wst.service;

import br.com.wanshitong.wst.entity.Editora;
import br.com.wanshitong.wst.exception.NoDataFoundException;
import br.com.wanshitong.wst.exception.RecordNotFoundException;
import br.com.wanshitong.wst.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    public Editora findById(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Editora", id.toString()));
    }

    public Editora findByNome(String nomeEditora){
        return editoraRepository.findByNomeEditoraContainingIgnoreCase(nomeEditora)
                .orElseThrow(() -> new RecordNotFoundException("Editora", nomeEditora));
    }

    public List<Editora> findAll(){
        List<Editora> editoras = editoraRepository.findAll();
        if(editoras.isEmpty()) throw new NoDataFoundException("Editora");
        return editoras;
    }
}
