package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.constant.OrderStatusConstant;
import org.gdutgoodfish.goodfish.pojo.dto.OrderDTO;
import org.gdutgoodfish.goodfish.pojo.entity.SecondHandOrder;
import org.gdutgoodfish.goodfish.mapper.SecondHandOrderMapper;
import org.gdutgoodfish.goodfish.service.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderImpl implements Order {
    @Autowired
    SecondHandOrderMapper secondHandOrderMapper;

    @Override
    public void newOrder(OrderDTO orderDTO) {
        SecondHandOrder secondHandOrder = null;
        secondHandOrder.builder()
                .orderId(orderDTO.getOrderId())
                .userId(Result.UserContext.getCurrentId())
                .sellerId(orderDTO.getSellerId())
                .status(OrderStatusConstant.ORDER_STATUS_UNPAID)
                .totalAmount(new BigDecimal(orderDTO.getOrderAmount()))
                .discountAmount(BigDecimal.valueOf(0))
                .payAmount(new BigDecimal(orderDTO.getOrderAmount()))
                .paymentMethod("")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                ;
        secondHandOrderMapper.insertOrder(secondHandOrder);

    }
}
