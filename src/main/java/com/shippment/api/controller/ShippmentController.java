package com.shippment.api.controller;

import com.shippment.api.model.ShipmentRequest;
import com.shippment.api.model.ShipmentResponse;
import com.shippment.api.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/shipment")
public class ShippmentController {

    final ShipmentService shipmentService;
    public ShippmentController(ShipmentService shipmentService1) {
        this.shipmentService = shipmentService1;
    }

    @PostMapping("/create")
    public ResponseEntity<Mono<ShipmentResponse>> createShipment(@RequestBody ShipmentRequest shipmentRequest) {
        Mono<ShipmentResponse> response = shipmentService.createShipment(shipmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Flux<ShipmentResponse>> getShipments() {
        return new ResponseEntity<>(shipmentService.getShipments(), HttpStatus.OK);
    }
}
