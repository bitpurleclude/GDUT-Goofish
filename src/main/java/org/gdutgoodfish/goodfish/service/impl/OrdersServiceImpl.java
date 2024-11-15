package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.pojo.entity.Orders;
import org.gdutgoodfish.goodfish.mapper.OrdersMapper;
import org.gdutgoodfish.goodfish.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
