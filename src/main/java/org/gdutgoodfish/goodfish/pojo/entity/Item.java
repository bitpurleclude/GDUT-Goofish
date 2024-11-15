package org.gdutgoodfish.goodfish.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("item")
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 图片
     */
    private String image;

    /**
     * 详情
     */
    private String description;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 位置
     */
    private String location;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
