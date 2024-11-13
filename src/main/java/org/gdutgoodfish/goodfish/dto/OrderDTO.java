package org.gdutgoodfish.goodfish.dto;

import lombok.Data;

@Data
public class OrderDTO {
    // 订单id
    private String orderId;
    // 订单名称
    private String orderName;
    // 订单金额
    private String orderAmount;
    // 商品描述
    private String orderDescription;
    // 用户id
    private Long sellerId;
    // 产品id
    private Long productId;
    // 产品数量
    private Integer quantity;
}
