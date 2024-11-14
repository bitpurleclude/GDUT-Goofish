package org.gdutgoodfish.goodfish.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.gdutgoodfish.goodfish.pojo.entity.SecondHandOrder;

import java.util.List;

@Mapper
public interface SecondHandOrderMapper {

    int insertOrder(SecondHandOrder order);

    SecondHandOrder selectOrderById(String orderId);

    List<SecondHandOrder> selectAllOrders();

    int updateOrderStatus(String orderId, Integer status);

    int deleteOrderById(String orderId);
}
