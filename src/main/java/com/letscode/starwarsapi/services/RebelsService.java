package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.dto.*;
import com.letscode.starwarsapi.exceptions.NoFindRebelException;
import com.letscode.starwarsapi.exceptions.NoRebelsException;
import com.letscode.starwarsapi.models.Rebel;
import com.letscode.starwarsapi.repositories.RebelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RebelsService {

    @Autowired
    private RebelsRepository rebelsRepository;

    public List<Rebel> getAllRebels(){
        List<Rebel> rebels = rebelsRepository.findAll();
        if(rebels.isEmpty()) throw new NoRebelsException("Não existe rebeldes cadastrados");
        return rebels;
    }

    public Rebel createRebel(RebelRequestDTO rebelRequest){
        Rebel rebel = new Rebel(rebelRequest);
        rebelsRepository.save(rebel);
        return rebel;
    }


    public Rebel findRebelById(Long id) {
        Rebel rebel = rebelsRepository.findById(id).orElseThrow(()-> new NoFindRebelException("id not found :" + id));    // Como o retorno é um optional, precisa do get. Tratar isso melhor depois
        return rebel;
    }

    public void deleteRebel(Long id) {
        rebelsRepository.deleteById(id);
    }



}
