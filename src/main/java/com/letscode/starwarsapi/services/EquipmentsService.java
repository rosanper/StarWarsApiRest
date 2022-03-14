package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.models.Equipment;
import com.letscode.starwarsapi.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentsService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment findEquipment(List<Equipment> listEquipment, String name){
        for (Equipment equipment : listEquipment) {
            if (equipment.getName() == name) return equipment;
        }
        return null;
    }
    public void deleteEquipment(Long id){
        equipmentRepository.deleteById(id);
    }
}
