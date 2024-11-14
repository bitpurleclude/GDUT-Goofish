package org.gdutgoodfish.goodfish.pojo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SecondHandPayment {

// 支付ID
private Long paymentId;
// 订单ID
private String orderId;
// 支付金额
private BigDecimal paymentAmount;
// 支付方式
private String paymentMethod;
// 状态
private Integer status;
// 交易ID
private String transactionId;
// 创建时间
private LocalDateTime createdAt;
    // Getters and Setters
}
