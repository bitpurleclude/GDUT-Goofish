package org.gdutgoodfish.goodfish.entity;

import java.time.LocalDateTime;

public class SecondHandShipment {

    private Long shipmentId;
    private String orderId;
    private Integer shipmentStatus;
    private String trackingNumber;
    private String carrier;
    private LocalDateTime shipmentDate;
    private LocalDateTime expectedDelivery;
    private LocalDateTime deliveredAt;

    // Getters and Setters
}
