package com.shippment.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Table(name = "shipment", schema = "practice")
public class ShipmentEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("shipment_id")
    private String shipmentId;

    @Column("order_id")
    private String orderId;

    @Column("shipment_create_time")
    private Timestamp shipmentCreateTime;
}
