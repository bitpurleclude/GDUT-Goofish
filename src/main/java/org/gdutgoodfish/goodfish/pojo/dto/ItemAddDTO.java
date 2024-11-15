package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddDTO {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

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

}
