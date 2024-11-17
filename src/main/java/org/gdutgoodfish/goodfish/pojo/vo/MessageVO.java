package org.gdutgoodfish.goodfish.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO implements Serializable {

    private Long id;

    private String sendUserName;

    private String receiveUserName;

    private String context;

    private LocalDateTime createTime;

    private Integer read;
}
