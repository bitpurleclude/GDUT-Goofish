package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.dto.OrderDTO;

public interface Order {
    void newOrder(OrderDTO orderDTO);
}
