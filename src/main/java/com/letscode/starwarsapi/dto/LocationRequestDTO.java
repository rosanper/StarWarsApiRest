package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequestDTO {

    @NotNull(message = "É preciso passar a latitude")
    private Integer latitude;
    @NotNull(message = "É preciso passar longitude")
    private Integer longitude;
    @NotBlank(message = "É preciso passar o nome do local ")
    private String name;
}
