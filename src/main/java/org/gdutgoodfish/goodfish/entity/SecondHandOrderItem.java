package org.gdutgoodfish.goodfish.entity;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class SecondHandOrderItem {

    @Id
    private Long itemId;

    private String orderId;

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String productCondition;

    private Boolean warrantyStatus;

    private LocalDateTime createdAt;

    // Getters and Setters
}
