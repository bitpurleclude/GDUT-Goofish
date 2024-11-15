package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.Data;

/**
 * @author guazai
 * @date 2024-11-15 14:35:51
 */
@Data
public class CommentPageQueryDTO {

    private Integer page;

    private Integer pageSize;

    private Long userId;

    private Long itemId;

    private String context;
}
