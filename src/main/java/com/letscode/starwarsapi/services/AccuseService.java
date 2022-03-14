package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.dto.RebelResponseDTO;
import com.letscode.starwarsapi.models.Rebel;
import com.letscode.starwarsapi.repositories.RebelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccuseService {

    @Autowired
    private RebelsRepository rebelsRepository;

    @Autowired
    private RebelsService rebelsService;

    public RebelResponseDTO accuseTraitor(Long id){
        Rebel rebel = rebelsService.findRebelById(id);
        int newQntAccusation = rebel.getQntAccusation() + 1;
        if (newQntAccusation >= 3) rebel.setIsTraitor(true);
        rebel.setQntAccusation(newQntAccusation);
        rebelsRepository.save(rebel);
        RebelResponseDTO rebelDTO = rebel.toDto();
        return rebelDTO;
    }

    public String accuseMessage(RebelResponseDTO rebelDTO){
        String messageIsTraitor;
        if(rebelDTO.getIsTraitor() == true){
            messageIsTraitor = " é um traidor!!!";
        }else{
            messageIsTraitor = " não é um traidor!";
        }
        String mensage = "O rebelde " + rebelDTO.getName() + " recebeu uma acusação de ser traidor, agora ele possui "
                + rebelDTO.getQntAccusation() +" acusações. O rebelde " + rebelDTO.getName() + messageIsTraitor;
        return mensage;
    }

}
