package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponseDTO {

    private Long id;
    private Integer latitude;
    private Integer longitude;
    private String name;
}
