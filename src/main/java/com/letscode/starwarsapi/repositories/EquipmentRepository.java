package com.letscode.starwarsapi.repositories;

import com.letscode.starwarsapi.models.Equipment;
import com.letscode.starwarsapi.models.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    Optional<Equipment> findEquipmentByNameAndRebel(String name, Rebel rebel);
}
