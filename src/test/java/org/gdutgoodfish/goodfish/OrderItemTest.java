package org.gdutgoodfish.goodfish;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.gdutgoodfish.goodfish.dto.OrderDTO;
import org.gdutgoodfish.goodfish.entity.SecondHandOrderItem;
import org.gdutgoodfish.goodfish.mapper.SecondHandOrderItemMapper;
import org.gdutgoodfish.goodfish.service.impl.OrderItemServiceImpl;

import java.math.BigDecimal;

class OrderItemServiceImplTest {

    @Mock
    private SecondHandOrderItemMapper secondHandOrderItemMapper;

    @InjectMocks
    private OrderItemServiceImpl orderItemServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void newOrderItem_createsOrderItemSuccessfully() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(String.valueOf(1L));
        orderDTO.setProductId(2L);
        orderDTO.setOrderName("Test Product");
        orderDTO.setQuantity(3);
        orderDTO.setProductPrice((int) 100.0);
        orderDTO.setOrderAmount((int) 300.0);

        orderItemServiceImpl.newOrderItem(orderDTO);

        verify(secondHandOrderItemMapper, times(1)).insertOrderItem(any(SecondHandOrderItem.class));
    }

    @Test
    void newOrderItem_handlesNullOrderDTO() {
        assertThrows(NullPointerException.class, () -> {
            orderItemServiceImpl.newOrderItem(null);
        });
    }

    @Test
    void newOrderItem_handlesNegativeQuantity() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(String.valueOf(1L));
        orderDTO.setProductId(2L);
        orderDTO.setOrderName("Test Product");
        orderDTO.setQuantity(-1);
        orderDTO.setProductPrice((int) 100.0);
        orderDTO.setOrderAmount((int) 300.0);

        assertThrows(IllegalArgumentException.class, () -> {
            orderItemServiceImpl.newOrderItem(orderDTO);
        });
    }

    @Test
    void newOrderItem_handlesZeroPrice() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(String.valueOf(1L));
        orderDTO.setProductId(2L);
        orderDTO.setOrderName("Test Product");
        orderDTO.setQuantity(3);
        orderDTO.setProductPrice(0);
        orderDTO.setOrderAmount(0);

        orderItemServiceImpl.newOrderItem(orderDTO);

        verify(secondHandOrderItemMapper, times(1)).insertOrderItem(any(SecondHandOrderItem.class));
    }
}