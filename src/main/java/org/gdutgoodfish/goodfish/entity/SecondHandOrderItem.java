package org.gdutgoodfish.goodfish.entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SecondHandOrderItem {

@Id
// 商品项ID
private Long itemId;

// 订单ID
private String orderId;

// 商品ID
private Long productId;

// 商品名称
private String productName;

// 数量
private Integer quantity;

// 价格
private BigDecimal price;

// 总价
private BigDecimal totalPrice;

// 商品状况
private String productCondition;

// 保修状态
private Boolean warrantyStatus;

// 创建时间
private LocalDateTime createdAt;
    // Getters and Setters
}
