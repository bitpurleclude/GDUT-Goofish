package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guazai
 * @date 2024-11-15 14:16:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentAddDTO {

    private Long itemId;

    private String context;
}
