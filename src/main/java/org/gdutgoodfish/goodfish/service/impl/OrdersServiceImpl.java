package org.gdutgoodfish.goodfish.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.exception.ItemException.ItemNotExistException;
import org.gdutgoodfish.goodfish.exception.orderException.OrderNotExistException;
import org.gdutgoodfish.goodfish.exception.orderException.OrderPayException;
import org.gdutgoodfish.goodfish.exception.orderException.OrderStatusException;
import org.gdutgoodfish.goodfish.pojo.common.AlipayOrder;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.OrderAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.OrderIdDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.entity.Orders;
import org.gdutgoodfish.goodfish.mapper.OrdersMapper;
import org.gdutgoodfish.goodfish.pojo.vo.OrderVO;
import org.gdutgoodfish.goodfish.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.util.AlipayUtil;
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
@RequiredArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    private final AlipayUtil alipayUtil;

    @Override
    public void addOrder(OrderAddDTO orderAddDTO) {
        Item item = Db.lambdaQuery(Item.class).eq(Item::getId, orderAddDTO.getItemId()).one();
        if (item == null) {
            throw new ItemNotExistException("商品不存在");
        }
        Orders order = Orders.builder()
                .number(System.currentTimeMillis() + "")
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

    @Override
    public String payOrder(Long orderId) {
        Orders orders = getById(orderId);
        if (orders == null) {
            throw new OrderNotExistException("订单不存在");
        }
        if (orders.getStatus() != 0) {
            throw new OrderStatusException("订单状态异常");
        }
        String price = orders.getPrice().toString();
        price = price.substring(0, price.indexOf('.') + 3);
        System.out.println(price);
        AlipayOrder alipayOrder = AlipayOrder.builder()
                .orderId(orderId)
                .out_trade_no(orders.getNumber())
                .product_code("FAST_INSTANT_TRADE_PAY")
                .subject(orders.getItemName())
                .total_amount(price)
                .build();
        String page;
        try {
            page = alipayUtil.pay(alipayOrder);
        } catch (AlipayApiException e) {
            throw new OrderPayException("订单支付异常");
        }
        return page;
    }

    @Override
    public void payOrderSuccess(String number) {
        lambdaUpdate().set(Orders::getStatus, 1).eq(Orders::getNumber, number).update();
    }
}
