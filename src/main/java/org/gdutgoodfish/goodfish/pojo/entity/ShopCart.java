package org.gdutgoodfish.goodfish.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_cart")
public class ShopCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 商品数量
     */
    private Integer itemNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品价格
     */
    private BigDecimal itemPrice;


}
