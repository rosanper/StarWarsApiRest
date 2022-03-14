package com.letscode.starwarsapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.starwarsapi.dto.EquipmentResponseDTO;
import com.letscode.starwarsapi.dto.EquipmentRequestDTO;
import com.letscode.starwarsapi.dto.EquipmentToTradeDTO;
import com.letscode.starwarsapi.enums.EquipmentsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer quantity;
    private Integer points;      //So esta sendo calculado quando utilizado o construtor

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rebel_id",referencedColumnName = "id")
    private Rebel rebel;

    public static Equipment of(EquipmentRequestDTO equipmentRequest){
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(equipmentRequest,equipment);
        return equipment;
    }

    public EquipmentResponseDTO toDto(){
        return EquipmentResponseDTO.builder()
                .id(id)
                .name(name)
                .quantity(quantity)
                .points(points)
                .build();
    }

    public Equipment(EquipmentRequestDTO equipmentRequest, Rebel rebel){
        name = EquipmentsEnum.getName(equipmentRequest.getName());   // Caso não exista o enum vai ter que tratar um IllegalArgumentException
        quantity = equipmentRequest.getQuantity();
        this.rebel = rebel;
        points = quantity* EquipmentsEnum.getPoints(equipmentRequest.getName());
    }

    public Equipment(EquipmentToTradeDTO equipmentequipmentToTrade, Rebel rebel){
        name = EquipmentsEnum.getName(equipmentequipmentToTrade.getName());   // Caso não exista o enum vai ter que tratar um IllegalArgumentException
        quantity = equipmentequipmentToTrade.getQuantity();
        this.rebel = rebel;
        points = quantity* EquipmentsEnum.getPoints(equipmentequipmentToTrade.getName());
    }
}
