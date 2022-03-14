package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.dto.ReportResponseDTO;
import com.letscode.starwarsapi.dto.ResourceAverageResponseDTO;
import com.letscode.starwarsapi.models.Equipment;
import com.letscode.starwarsapi.models.Rebel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private RebelsService rebelsService;

    public ResourceAverageResponseDTO getAverageEquipment(List<Rebel> rebels, double totalRebels){
        int quantityWater = 0;
        int quantityFood = 0;
        int quantityWeapon = 0;
        int quantityAmmo = 0;

        for (Rebel rebel : rebels) {
            for (Equipment equipment : rebel.getEquipments()) {
                if(equipment.getName()=="Arma"){
                    quantityWeapon += equipment.getQuantity();
                }
                if(equipment.getName()=="Municao"){
                    quantityAmmo += equipment.getQuantity();
                }
                if(equipment.getName()=="Agua"){
                    quantityWater += equipment.getQuantity();
                }
                if(equipment.getName()=="Comida"){
                    quantityFood += equipment.getQuantity();
                }
            }
        }

        double averageWeapon = quantityWeapon/totalRebels;
        double averageAmmo = quantityAmmo/totalRebels;
        double averageWater = quantityWater/totalRebels;
        double averageFood = quantityFood/totalRebels;

        return ResourceAverageResponseDTO.builder()
                .averageAmmo(averageAmmo)
                .averageFood(averageFood)
                .averageWater(averageWater)
                .averageWeapon(averageWeapon)
                .totalRebels(totalRebels)
                .build();
    }

    public ReportResponseDTO createReport(){
        List<Rebel> traitors = new ArrayList<>();
        List<Rebel> rebels = new ArrayList<>();
        List<Rebel> allRebels = rebelsService.getAllRebels();

        // separar traidores e rebeldes
        for (Rebel rebel : allRebels) {
            if(rebel.getIsTraitor()==false){
                rebels.add(rebel);
            }else{
                traitors.add(rebel);
            }
        }

        double total = allRebels.size();
        double totalRebels = rebels.size();
        double totalTraitors = traitors.size();

        //Percentual de rebeldes e traidores
        double percentRebels = (totalRebels / total) * 100;
        double percentTraitors = (totalTraitors / total) * 100;

        //Pontos perdidos
        int totalPointsTraitor = traitors.stream().mapToInt(Rebel::getRebelPoints).sum();

        //Media de equipamentos
        ResourceAverageResponseDTO resourceAverageResponseDTO = getAverageEquipment(rebels, totalRebels);

        ReportResponseDTO reportResponseDTO = ReportResponseDTO.builder()
                .rebelPercentage(percentRebels)
                .traitorsPercentage(percentTraitors)
                .resourceAverage(resourceAverageResponseDTO)
                .lostPoints(totalPointsTraitor)
                .build();

        return reportResponseDTO;
    }
}
