package com.shippment.api.service;

import com.shippment.api.entity.ShipmentEntity;
import com.shippment.api.model.ShipmentRequest;
import com.shippment.api.model.ShipmentResponse;
import com.shippment.api.repository.ShipmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ShipmentService {

    final ShipmentRepository shipmentRepository;
    final ModelMapper modelMapper;

    public ShipmentService(ShipmentRepository shipmentRepository, ModelMapper modelMapper) {
        this.shipmentRepository = shipmentRepository;
        this.modelMapper = modelMapper;
    }


    public Mono<ShipmentResponse> createShipment(ShipmentRequest shipmentRequest) {
        String shipmentId = UUID.randomUUID().toString();
        ShipmentEntity shipmentEntity = ShipmentEntity.builder()
                .shipmentId(shipmentId)
                .orderId(shipmentRequest.getOrderId())
                .shipmentCreateTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        Mono<ShipmentEntity> shipmentEntityMono = shipmentRepository.save(shipmentEntity);
        return shipmentEntityMono.map(entity -> modelMapper.map(entity, ShipmentResponse.class));
    }

    public Flux<ShipmentResponse> getShipments() {
        return shipmentRepository.findAll().map(entity -> modelMapper.map(entity, ShipmentResponse.class));
        //shipmentRepository.findAll().flatMap(entity -> shipmentRepository.findById(entity.getId()));
    }
}
