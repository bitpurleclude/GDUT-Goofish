package org.gdutgoodfish.goodfish.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class SecondHandOrder {

    @Id
// 订单ID
private String orderId;

// 用户ID
private Long userId;

// 卖家ID
private Long sellerId;

// 订单状态
private Integer status;

// 总金额
private BigDecimal totalAmount;

// 折扣金额
private BigDecimal discountAmount;

// 支付金额
private BigDecimal payAmount;

// 支付方式
private String paymentMethod;

// 创建时间
private LocalDateTime createdAt;

// 更新时间
private LocalDateTime updatedAt;


}
