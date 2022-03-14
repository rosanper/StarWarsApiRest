package com.letscode.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponseDTO {
    private Double traitorsPercentage;
    private Double rebelPercentage;
    private ResourceAverageResponseDTO resourceAverage;
    private Integer lostPoints;
}
