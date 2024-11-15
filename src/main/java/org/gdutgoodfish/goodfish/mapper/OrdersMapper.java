package org.gdutgoodfish.goodfish.mapper;

import io.swagger.models.auth.In;
import org.gdutgoodfish.goodfish.pojo.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.gdutgoodfish.goodfish.pojo.vo.OrderVO;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    List<OrderVO> selectOrderByUserId(Long userId, Integer status);
}
