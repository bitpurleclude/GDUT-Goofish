package org.gdutgoodfish.goodfish.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderVO {
    @TableId(value = "id", type = IdType.AUTO)

    private Long id;

    private Long itemId;

    private String number;

    private String sellUser;

    private Integer status;

    private String source;

    private String target;

    private LocalDateTime creatTime;

    private BigDecimal price;

    private String itemName;

    private String itemImage;
}
