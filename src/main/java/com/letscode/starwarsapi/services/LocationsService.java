package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.dto.LocationRequestDTO;
import com.letscode.starwarsapi.models.Location;
import com.letscode.starwarsapi.models.Rebel;
import com.letscode.starwarsapi.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationsService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RebelsService rebelsService;

    public Rebel updateLocalization(Long id, LocationRequestDTO localizationRequest){
        Rebel rebel = rebelsService.findRebelById(id);
        Location newLocation = new Location(localizationRequest,rebel);
        locationRepository.save(newLocation);
        return rebel;
    }
}
