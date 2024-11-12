package org.gdutgoodfish.goodfish.entity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class SecondHandOrder {

    @Id
    private String orderId;

    private Long userId;

    private Long sellerId;

    private Integer status;

    private BigDecimal totalAmount;

    private BigDecimal discountAmount;

    private BigDecimal payAmount;

    private String paymentMethod;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Getters and Setters
}
