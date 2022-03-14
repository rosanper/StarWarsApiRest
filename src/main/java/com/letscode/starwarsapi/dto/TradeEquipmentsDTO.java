package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeEquipmentsDTO {
    private TradeRebelsDTO rebel1;
    private TradeRebelsDTO rebel2;
}
