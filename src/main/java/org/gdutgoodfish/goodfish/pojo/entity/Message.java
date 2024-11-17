package org.gdutgoodfish.goodfish.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("message")
@ApiModel(value="Message对象", description="消息表")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("send_id")
    private Long sendId;

    @TableField("receive_id")
    private Long receiveId;

    @TableField("context")
    private String context;

    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否读")
    @TableField("`read`")
    private Integer read;


}
