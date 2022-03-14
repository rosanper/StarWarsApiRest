package com.letscode.starwarsapi.services;

import com.letscode.starwarsapi.dto.EquipmentToTradeDTO;
import com.letscode.starwarsapi.dto.TradeEquipmentsDTO;
import com.letscode.starwarsapi.enums.EquipmentsEnum;
import com.letscode.starwarsapi.exceptions.TradeException;
import com.letscode.starwarsapi.models.Equipment;
import com.letscode.starwarsapi.models.Rebel;
import com.letscode.starwarsapi.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentsService equipmentsService;

    @Autowired
    private RebelsService rebelsService;

    public void verifyTraitor(Rebel rebel1, Rebel rebel2){
        if(rebel1.getIsTraitor()== true || rebel2.getIsTraitor()== true){
            throw new TradeException("pelo menos um dos rebeldes é um traidor");
        }
    }

    public void verifyNameAndQuantity(List<EquipmentToTradeDTO> equipmentToTradeList, List<Equipment> equipmentListRebelToGive){
        for (EquipmentToTradeDTO equipmentToTrade : equipmentToTradeList) {
            Equipment equipment = equipmentsService.findEquipment(equipmentListRebelToGive, equipmentToTrade.getName());

            if(equipment == null){
                throw new TradeException("O equipamento selecionado para a troca não existe");
            }else{
                int newQuantity = equipment.getQuantity() - equipmentToTrade.getQuantity();
                if (newQuantity < 0) throw new TradeException("Não existe quantidade suficiente do item selecionado para a troca");
            }
        }
    }

    public void verifyPoints(List<EquipmentToTradeDTO> equipmentToTradeList1, List<EquipmentToTradeDTO> equipmentToTradeList2){
        int pointsRebel1 = equipmentToTradeList1.stream()
                .mapToInt(equipmentToTrade -> equipmentToTrade.getPoints()).sum();

        int pointsRebel2 = equipmentToTradeList2.stream()
                .mapToInt(equipmentToTrade -> equipmentToTrade.getPoints()).sum();

        int difference = pointsRebel1 - pointsRebel2;
        if(difference != 0){
            throw new TradeException("A troca não respeita a igualdade de pontuação. A diferença de pontos é: " + difference);
        }
    }


    public void verifyConditions(List<Equipment> equipmentsRebel1, List<Equipment> equipmentsRebel2,
                                   List<EquipmentToTradeDTO> equipmentRequestToChangeRebel1,
                                   List<EquipmentToTradeDTO> equipmentRequestToChangeRebel2 ){

        // se tem o equipamento e a quantiddade
        verifyNameAndQuantity(equipmentRequestToChangeRebel1, equipmentsRebel1);
        verifyNameAndQuantity(equipmentRequestToChangeRebel2, equipmentsRebel2);

        // pontuacao
        verifyPoints(equipmentRequestToChangeRebel1,equipmentRequestToChangeRebel2);

    }

    public void tradeEquipment(Rebel rebelToReceive, List<EquipmentToTradeDTO> equipmentToTradeList,
                               List<Equipment> equipmentListRebelToGive, List<Equipment> equipmentListRebelToReceive){

        for (EquipmentToTradeDTO equipmentToTrade : equipmentToTradeList) {
            Equipment equipment = equipmentsService.findEquipment(equipmentListRebelToGive, equipmentToTrade.getName());

            int newQuantity = equipment.getQuantity() - equipmentToTrade.getQuantity();
            int newPoints = newQuantity * EquipmentsEnum.getPoints(equipment.getName());

            equipment.setQuantity(newQuantity);
            equipment.setPoints(newPoints);
            equipmentRepository.save(equipment);

            Equipment newEquipmentRebel2 = equipmentsService.findEquipment(equipmentListRebelToReceive, equipmentToTrade.getName());
            if(newEquipmentRebel2==null){
                newEquipmentRebel2 = new Equipment(equipmentToTrade,rebelToReceive);
            }else{
                int newQuantityEquipment = newEquipmentRebel2.getQuantity()+ equipmentToTrade.getQuantity();
                int newPointsEquipment = newQuantityEquipment * EquipmentsEnum.getPoints(equipment.getName());
                newEquipmentRebel2.setQuantity(newQuantityEquipment);
                newEquipmentRebel2.setPoints(newPointsEquipment);
            }

            equipmentRepository.save(newEquipmentRebel2);
        }
    }

    public String changeEquipments(TradeEquipmentsDTO tradeEquipmentsDTO){

        List<EquipmentToTradeDTO> equipmentRequestToChangeRebel1 = tradeEquipmentsDTO.getRebel1().getEquipmentRequestDTOList().stream().map(equipmentRequest -> equipmentRequest.toTrade()).collect(Collectors.toList());
        List<EquipmentToTradeDTO> equipmentRequestToChangeRebel2 = tradeEquipmentsDTO.getRebel2().getEquipmentRequestDTOList().stream().map(equipmentRequest -> equipmentRequest.toTrade()).collect(Collectors.toList());

        Long idRebel1 = tradeEquipmentsDTO.getRebel1().getId();
        Long idRebel2 = tradeEquipmentsDTO.getRebel2().getId();

        Rebel rebel1 = rebelsService.findRebelById(idRebel1);
        Rebel rebel2 = rebelsService.findRebelById(idRebel2);

        // tem traidor
        verifyTraitor(rebel1, rebel2);

        List<Equipment> equipmentsRebel1 = rebel1.getEquipments();
        List<Equipment> equipmentsRebel2 = rebel2.getEquipments();

        //verificar condições
        verifyConditions(equipmentsRebel1,equipmentsRebel2,equipmentRequestToChangeRebel1,equipmentRequestToChangeRebel2);

        // Fazer a troca
        tradeEquipment(rebel2,equipmentRequestToChangeRebel1,equipmentsRebel1,equipmentsRebel2);
        tradeEquipment(rebel1,equipmentRequestToChangeRebel2,equipmentsRebel2, equipmentsRebel1);

        return "A troca foi realizada com sucesso!!!";
    }

}
