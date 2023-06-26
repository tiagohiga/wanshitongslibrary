package br.com.wanshitong.wst.service;

import br.com.wanshitong.wst.entity.Genero;
import br.com.wanshitong.wst.exception.NoDataFoundException;
import br.com.wanshitong.wst.exception.RecordNotFoundException;
import br.com.wanshitong.wst.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public Genero findById(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Genero", id.toString()));
    }

    public Genero findByNome(String nomeGenero){
        return generoRepository.findByNomeGeneroContainingIgnoreCase(nomeGenero)
                .orElseThrow(() -> new RecordNotFoundException("Genero", nomeGenero));
    }

    public List<Genero> findAll(){
        List<Genero> generos = generoRepository.findAll();
        if(generos.isEmpty()) throw new NoDataFoundException("Genero");
        return generos;
    }
}
