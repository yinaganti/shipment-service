package com.shippment.api.service;

import com.shippment.api.model.ShipmentRequest;
import com.shippment.api.model.ShipmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class KafkaConsumer {

    final ObjectMapper objectMapper;
    final ShipmentService shipmentService;

    public KafkaConsumer(ObjectMapper objectMapper, ShipmentService shipmentService) {
        this.objectMapper = objectMapper;
        this.shipmentService = shipmentService;
    }

    @KafkaListener(topics = "${spring.kafka.order.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeShipment(String request) {
        log.info("Received Shipment Request: {}", request);
        ShipmentRequest shipmentRequest = objectMapper.readValue(request, ShipmentRequest.class);
        Mono<ShipmentResponse> response =  shipmentService.createShipment(shipmentRequest);
        response.subscribe((shipment)->log.info("shipment id = {} ", shipment.getShipmentId()));
    }
}
