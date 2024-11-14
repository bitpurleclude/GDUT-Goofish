package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.pojo.dto.OrderDTO;
import org.gdutgoodfish.goodfish.pojo.entity.SecondHandOrderItem;
import org.gdutgoodfish.goodfish.mapper.SecondHandOrderItemMapper;
import org.gdutgoodfish.goodfish.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    SecondHandOrderItemMapper secondHandOrderItemMapper;

    /**
     * 新增订单项
     */
public void newOrderItem(OrderDTO orderDTO) {
    if (orderDTO == null) {
        throw new NullPointerException("OrderDTO cannot be null");
    }
    if (orderDTO.getQuantity() < 0) {
        throw new IllegalArgumentException("Quantity cannot be negative");
    }
    if (orderDTO.getProductPrice() < 0) {
        throw new IllegalArgumentException("Product price cannot be negative");
    }
    if (orderDTO.getOrderAmount() < 0) {
        throw new IllegalArgumentException("Order amount cannot be negative");
    }

    SecondHandOrderItem secondHandOrderItem = SecondHandOrderItem.builder()
            .itemId(Long.valueOf(orderDTO.getOrderId()))
            .orderId(orderDTO.getOrderId())
            .productId(orderDTO.getProductId())
            .productName(orderDTO.getOrderName())
            .quantity(orderDTO.getQuantity())
            .price(BigDecimal.valueOf(orderDTO.getProductPrice()))
            .totalPrice(BigDecimal.valueOf(orderDTO.getOrderAmount()))
            .productCondition(null)
            .warrantyStatus(false)
            .createdAt(LocalDateTime.now())
            .build();

    secondHandOrderItemMapper.insertOrderItem(secondHandOrderItem);
}
}
