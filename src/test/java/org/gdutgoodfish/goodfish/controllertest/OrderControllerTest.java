package org.gdutgoodfish.goodfish.controllertest;

import cn.hutool.core.lang.Snowflake;
import org.gdutgoodfish.goodfish.controller.OrderController;
import org.gdutgoodfish.goodfish.dto.OrderDTO;
import org.gdutgoodfish.goodfish.service.impl.Alipay;
import org.gdutgoodfish.goodfish.service.impl.OrderImpl;
import org.gdutgoodfish.goodfish.service.impl.OrderItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private Alipay alipay;

    @Mock
    private Snowflake snowflake;

    @Mock
    private OrderImpl orderImpl;

    @Mock
    private OrderItemServiceImpl orderItemServiceImpl;

    @InjectMocks
    private OrderController orderController;

    @Test
    void newOrder_createsOrderSuccessfully() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductId(Long.valueOf("123"));
        orderDTO.setOrderName("Test Order");
        orderDTO.setQuantity(1);
        orderDTO.setProductPrice((int) 100.0);
        orderDTO.setOrderAmount((int) 100.0);

        when(snowflake.nextIdStr()).thenReturn("1");

        OrderDTO result = orderController.newOrder(orderDTO);

        assertNotNull(result);
        assertEquals("1", result.getOrderId());
        verify(orderImpl).newOrder(orderDTO);
        verify(orderItemServiceImpl).newOrderItem(orderDTO);
    }

    @Test
    void newOrder_handlesNullOrderDTO() {
        assertThrows(NullPointerException.class, () -> orderController.newOrder(null));
    }

    @Test
    void newOrder_handlesNegativeQuantity() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setQuantity(-1);

        assertThrows(IllegalArgumentException.class, () -> orderController.newOrder(orderDTO));
    }

}