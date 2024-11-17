package org.gdutgoodfish.goodfish.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO {

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
    private String username;

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
    private String categoryName;

    /**
     * 位置
     */
    private String location;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
