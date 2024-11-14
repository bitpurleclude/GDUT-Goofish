package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO {

    private String title;

    private String description;

    private BigDecimal price;

    private Integer categoryId;

    private String location;
}
