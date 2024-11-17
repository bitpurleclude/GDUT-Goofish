package org.gdutgoodfish.goodfish.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author guazai
 * @date 2024-11-15 21:24:49
 */
@Data
public class MessageAddDTO {

    @TableField("receive_id")
    private Long receiveId;

    @TableField("context")
    private String context;
}
