package org.gdutgoodfish.goodfish.mapper;

import com.alipay.api.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import org.gdutgoodfish.goodfish.pojo.entity.SecondHandOrderItem;

@Mapper
public interface SecondHandOrderItemMapper {

 /**
 * 插入一个新的订单项到数据库中。
 *
 * @param secondHandOrderItem 要插入的订单项
 * @return 受影响的行数
 */
@Insert("INSERT INTO second_hand_order_item(" +
        "item_id," +
        "order_id," +
        " product_id," +
        " product_name," +
        " quantity, " +
        "price," +
        "total_price," +
        " product_condition," +
        " warranty_status ," +
        "created_at" +
        ") VALUES(" +
        " #{itemId}," +
        "#{orderId}," +
        " #{productId}," +
        " #{productName}," +
        " #{quantity}," +
        " #{price}," +
        " #{totalPrice}," +
        " #{productCondition}," +
        " #{warrantyStatus}," +
        " #{createdAt}" +
        ")"
)
int insertOrderItem(SecondHandOrderItem secondHandOrderItem);
/**
 * 更新数据库中的订单项。
 *
 * @param orderItem 要更新的订单项
 * @return 受影响的行数
 */
int updateOrderItem(OrderItem orderItem);
/**
 * 根据ID删除订单项。
 *
 * @param id 要删除的订单项的ID
 * @return 受影响的行数
 */
@Delete("DELETE FROM second_hand_order_item WHERE order_id = #{id}")
int deleteOrderItemById(int id);
/**
 * 根据ID查询订单项。
 *
 * @param id 要查询的订单项的ID
 * @return 查询到的订单项
 */
 @Select("SELECT * FROM second_hand_order_item WHERE order_id = #{id}")
    OrderItem selectOrderItemById(int id);

}