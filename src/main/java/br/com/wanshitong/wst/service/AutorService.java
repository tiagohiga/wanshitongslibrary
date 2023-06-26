package br.com.wanshitong.wst.service;

import br.com.wanshitong.wst.entity.Autor;
import br.com.wanshitong.wst.exception.NoDataFoundException;
import br.com.wanshitong.wst.exception.RecordNotFoundException;
import br.com.wanshitong.wst.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Autor findById(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Autor", id.toString()));
    }

    public Autor findByNome(String nomeAutor){
        return autorRepository.findByNomeAutorContainingIgnoreCase(nomeAutor)
                .orElseThrow(() -> new RecordNotFoundException("Autor", nomeAutor));
    }

    public List<Autor> findAll(){
        List<Autor> autors = autorRepository.findAll();
        if(autors.isEmpty()) throw new NoDataFoundException("Autor");
        return autors;
    }
}
