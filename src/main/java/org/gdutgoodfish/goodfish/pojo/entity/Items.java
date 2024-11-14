package org.gdutgoodfish.goodfish.pojo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author J
 * @since 2024-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer itemId;

    private String title;

    private String description;

    private BigDecimal price;

    private Integer categoryId;

    private String location;

    private LocalDateTime postedDate;

    private Integer userId;


}
