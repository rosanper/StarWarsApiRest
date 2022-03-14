package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRebelsDTO {

    @NotNull(message = "É preciso passar o id do rebelde que vai fazer a troca")
    private Long id;

    @NotNull(message = "É preciso passar o equipamento que vai fazer parte da troca")
    private List<EquipmentRequestDTO> equipmentRequestDTOList;
}
