package org.gdutgoodfish.goodfish.entity;

import java.time.LocalDateTime;

public class SecondHandShipment {

// 发货ID
private Long shipmentId;
// 订单ID
private String orderId;
// 发货状态
private Integer shipmentStatus;
// 追踪号码
private String trackingNumber;
// 承运人
private String carrier;
// 发货日期
private LocalDateTime shipmentDate;
// 预计送达日期
private LocalDateTime expectedDelivery;
// 实际送达日期
private LocalDateTime deliveredAt;
    // Getters and Setters
}
