package com.letscode.starwarsapi.dto;

import com.letscode.starwarsapi.enums.EquipmentsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRequestDTO {

    @NotBlank(message = "É preciso passar o nome do equipmento")
    private String name;

    @NotNull(message = "É preciso passar a quantidade do equipmento")
    @Min(value = 1,message = "A quantidade de itens tem que ser pelo menos 1")
    private Integer quantity;

    public EquipmentToTradeDTO toTrade(){
        return EquipmentToTradeDTO.builder()
                .name(EquipmentsEnum.getName(name))
                .quantity(quantity)
                .points(quantity* EquipmentsEnum.getPoints(name))
                .build();
    }

}
