package com.letscode.starwarsapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EquipmentsEnum {
    ARMA("Arma",4),
    MUNICAO("Municao", 3),
    AGUA("Agua", 2),
    COMIDA("Comida", 1);

    private String equipmentName;
    private Integer equipmentPoints;

    public static String getName(String name){
        return EquipmentsEnum.valueOf(name.toUpperCase()).getEquipmentName();
    }

    public static int getPoints(String name){
        return EquipmentsEnum.valueOf(name.toUpperCase()).getEquipmentPoints();
    }
}
