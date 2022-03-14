package com.letscode.starwarsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.starwarsapi.dto.LocationResponseDTO;
import com.letscode.starwarsapi.dto.LocationRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer latitude;
    private Integer longitude;
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rebel_id", referencedColumnName = "id")
    private Rebel rebelLocalization;

    public static Location of(LocationRequestDTO localizationRequest){
        Location location = new Location();
        BeanUtils.copyProperties(localizationRequest, location);
        return location;
    }

    public LocationResponseDTO toDto(){
        return LocationResponseDTO.builder()
                .id(id)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .build();
    }

    public Location(LocationRequestDTO localizationRequest, Rebel rebel){
        latitude = localizationRequest.getLatitude();
        longitude = localizationRequest.getLongitude();
        name = localizationRequest.getName();
        rebelLocalization = rebel;
    }
}
