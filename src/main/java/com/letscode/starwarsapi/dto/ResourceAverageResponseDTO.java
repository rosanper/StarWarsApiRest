package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceAverageResponseDTO {

    private Double averageWeapon;
    private Double averageAmmo;
    private Double averageWater;
    private Double averageFood;
    private Double totalRebels;
}
