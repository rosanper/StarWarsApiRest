package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.dto.*;
import com.letscode.starwarsapi.models.Rebel;
import com.letscode.starwarsapi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rebels")
public class RebelsController {

    @Autowired
    private RebelsService rebelsService;

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private EquipmentsService equipmentsService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private AccuseService accuseService;



    @GetMapping
    public List<RebelResponseDTO> listRebels(){
        List<RebelResponseDTO> rebels = rebelsService.getAllRebels().stream()
                .map(rebel -> rebel.toDto()).collect(Collectors.toList());
        return rebels;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RebelResponseDTO> findRebelById(@PathVariable("id") Long id){
        Rebel rebel = rebelsService.findRebelById(id);
        RebelResponseDTO rebelDTO = rebel.toDto();
        return ResponseEntity.ok(rebelDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RebelResponseDTO createRebel(@Valid @RequestBody RebelRequestDTO rebelRequest){
        Rebel rebel = rebelsService.createRebel(rebelRequest);
        RebelResponseDTO rebelDTO = rebel.toDto();
        return rebelDTO;
    }

    @PutMapping(value = "/{id}/accusation")
    public String accuseRebel(@PathVariable("id") Long id){
        RebelResponseDTO rebelDTO = accuseService.accuseTraitor(id);
        return accuseService.accuseMessage(rebelDTO);
    }

    @PutMapping (value = "/{id}/updateLocation")
    public RebelResponseDTO updateLocation(@PathVariable Long id, @Valid @RequestBody LocationRequestDTO localizationRequest){
        Rebel updateRebel = locationsService.updateLocalization(id,localizationRequest);
        return updateRebel.toDto();
    }

    @PutMapping(value = "/tradeEquipment")
    public String trade(@RequestBody TradeEquipmentsDTO tradeEquipmentsDTO){
        return tradeService.changeEquipments(tradeEquipmentsDTO);
    }

    @GetMapping(value = "/report")
    public ReportResponseDTO report(){
        return reportService.createReport();
    }

//    @DeleteMapping(value = "/deleteEquipment/{id}")
//    public void deleteEquipment(@PathVariable("id") Long id){
//        equipmentsService.deleteEquipment(id);
//    }

//    @DeleteMapping(value = "/{id}")
//    public void deleteRebel(@PathVariable("id") Long id){
//        rebelsService.deleteRebel(id);
//    }
}
