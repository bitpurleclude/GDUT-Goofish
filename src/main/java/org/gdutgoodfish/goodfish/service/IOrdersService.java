package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.pojo.dto.OrderAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.OrderIdDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import org.gdutgoodfish.goodfish.pojo.vo.OrderVO;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
public interface IOrdersService extends IService<Orders> {

    void addOrder(OrderAddDTO orderAddDTO);

    List<OrderVO> getUserOrder(Integer status);

    void finishOrder(OrderIdDTO orderIdDTO);

    void cancelOrder(OrderIdDTO orderIdDTO);

    String payOrder(Long orderId);

    void payOrderSuccess(String number);
}
