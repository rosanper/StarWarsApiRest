package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RebelResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private String gender;

    private Integer qntAccusation;
    private Boolean isTraitor;

    List<EquipmentResponseDTO> equipmentList = new ArrayList<>();

    private LocationResponseDTO lastLocalization;

    private Integer points;

}
