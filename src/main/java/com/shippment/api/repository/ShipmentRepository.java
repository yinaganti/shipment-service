package com.shippment.api.repository;

import com.shippment.api.entity.ShipmentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends ReactiveCrudRepository<ShipmentEntity, Long> {

}
