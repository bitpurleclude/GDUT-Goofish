package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.Data;
import org.gdutgoodfish.goodfish.annotation.CannotNull;

@Data
 /**
  *订单DTO
  * @Param orderId 订单ID
  * @Param orderName 订单名称
  * @Param orderAmount 订单金额
  * @Param orderDescription 商品描述
  * @Param sellerId 卖家ID
  * @Param productId 产品ID
  * @Param quantity 产品数量
  */
public class OrderDTO {
    // 订单id
    /**
      * 订单ID
     */
    private String orderId;
    // 订单名称
    private String orderName;
    // 商品单价
    @CannotNull
    private int productPrice;
    // 订单金额
    @CannotNull
    private int orderAmount;
    // 商品描述

    private String orderDescription;
    // 用户id
    @CannotNull
    private Long sellerId;
    // 产品id
    @CannotNull
    private Long productId;
    // 产品数量
    @CannotNull
    private Integer quantity;
}
