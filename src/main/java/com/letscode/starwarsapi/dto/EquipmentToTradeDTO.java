package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentToTradeDTO {

    String name;
    Integer quantity;
    Integer points;

}
