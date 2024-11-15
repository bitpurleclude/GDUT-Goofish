package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPageQueryDTO {

    private Long page;
    private Long pageSize;
    private String itemName;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String location;
}
