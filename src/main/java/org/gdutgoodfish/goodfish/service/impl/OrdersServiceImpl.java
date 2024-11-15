package org.gdutgoodfish.goodfish.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.gdutgoodfish.goodfish.exception.ItemException.ItemNotExistException;
import org.gdutgoodfish.goodfish.exception.orderException.OrderNotExistException;
import org.gdutgoodfish.goodfish.exception.orderException.OrderStatusException;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.OrderAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.OrderIdDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.entity.Orders;
import org.gdutgoodfish.goodfish.mapper.OrdersMapper;
import org.gdutgoodfish.goodfish.pojo.vo.OrderVO;
import org.gdutgoodfish.goodfish.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Override
    public void addOrder(OrderAddDTO orderAddDTO) {
        Item item = Db.lambdaQuery(Item.class).eq(Item::getId, orderAddDTO.getItemId()).one();
        if (item == null) {
            throw new ItemNotExistException("商品不存在");
        }
        Orders order = Orders.builder()
                .number(UUID.randomUUID().toString().replaceAll("-", ""))
                .itemId(item.getId())
                .itemImage(item.getImage())
                .itemName(item.getName())
                .source(item.getLocation())
                .price(item.getPrice())
                .userId(UserContext.getCurrentId())
                .target(orderAddDTO.getTarget())
                .status(0)
                .creatTime(LocalDateTime.now())
                .build();
        save(order);
    }

    @Override
    public List<OrderVO> getUserOrder(Integer status) {
        return baseMapper.selectOrderByUserId(UserContext.getCurrentId(), status);
    }

    @Override
    public void finishOrder(OrderIdDTO orderIdDTO) {
        Long orderId = orderIdDTO.getOrderId();
        Orders orders = getById(orderId);
        if (orders == null) {
            throw new OrderNotExistException("订单不存在");
        }
        if (orders.getStatus() != 1) {
            throw new OrderStatusException("订单状态异常");
        }
        orders.setStatus(2);
        updateById(orders);
    }

    @Override
    public void cancelOrder(OrderIdDTO orderIdDTO) {
        Orders orders = getById(orderIdDTO.getOrderId());
        if (orders == null) {
            throw new OrderNotExistException("订单不存在");
        }
        if (orders.getStatus() != 0) {
            throw new OrderStatusException("订单状态异常");
        }
        orders.setStatus(3);
        updateById(orders);
    }
}
