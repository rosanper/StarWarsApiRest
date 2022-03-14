package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RebelRequestDTO {

    @NotBlank(message = "É preciso passar o nome do rebelde")
    private String name;
    @NotNull(message = "É preciso passar a idade")
    @Min(value = 1, message = "A idade tem que ser maior que 0")
    private Integer age;
    @NotBlank(message = "É preciso passar o genero")
    private String gender;

    @Valid
    private List<EquipmentRequestDTO> equipmentsRequest = new ArrayList<>();

    @Valid
    @NotNull(message = "É preciso passar uma localização")
    private List<LocationRequestDTO> locationRequestList;
}
