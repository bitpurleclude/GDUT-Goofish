package org.gdutgoodfish.goodfish.pojo.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
@ApiModel(value="Orders对象", description="订单表")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("item_id")
    private Long itemId;

    @ApiModelProperty(value = "订单号")
    @TableField("number")
    private String number;

    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "0已下单待支付1已支付2已接收")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "发货地")
    @TableField("source")
    private String source;

    @ApiModelProperty(value = "收货地")
    @TableField("target")
    private String target;

    @TableField("creat_time")
    private LocalDateTime creatTime;

    @TableField("price")
    private BigDecimal price;

    @TableField("item_name")
    private String itemName;

    @TableField("item_image")
    private String itemImage;


}
