package com.letscode.starwarsapi.models;

import com.letscode.starwarsapi.dto.RebelResponseDTO;
import com.letscode.starwarsapi.dto.RebelRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
//@Table(name = "rebels_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rebel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String gender;

    private Integer qntAccusation;
    private Boolean isTraitor;

    @OneToMany(mappedBy = "rebel", cascade = CascadeType.ALL)
    List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "rebelLocalization", cascade = CascadeType.ALL)
    List<Location> locations;

    private Integer rebelPoints;

    public static Rebel of(RebelRequestDTO rebelRequest){
        Rebel rebel = new Rebel();
        BeanUtils.copyProperties(rebelRequest,rebel);
        return rebel;
    }

    public RebelResponseDTO toDto(){
        return RebelResponseDTO.builder()
                .id(id)
                .name(name)
                .age(age)
                .gender(gender)
                .qntAccusation(qntAccusation)
                .isTraitor(isTraitor)
                .equipmentList(equipments.stream()
                        .map(equipment -> equipment.toDto()).collect(Collectors.toList()))
                .lastLocalization(locations.get(locations.size()-1).toDto()) //Repensar a obtencao do indice
                .points(rebelPoints)
                .build();
    }

    public Rebel(RebelRequestDTO rebelRequest){
        name = rebelRequest.getName();
        age = rebelRequest.getAge();
        gender = rebelRequest.getGender();
        equipments = rebelRequest.getEquipmentsRequest()
                .stream().map(equipmentRequest -> new Equipment(equipmentRequest,this))
                .collect(Collectors.toList());
        locations = rebelRequest.getLocationRequestList()
                .stream().map(location -> new Location(location,this))
                .collect(Collectors.toList());
        qntAccusation = 0;
        isTraitor = false;
        rebelPoints = equipments.stream().mapToInt(equipment -> equipment.getPoints()).sum(); //o calculo esta sendo feito somente quando é criado e não é refeito quando é atualizado
    }
}
