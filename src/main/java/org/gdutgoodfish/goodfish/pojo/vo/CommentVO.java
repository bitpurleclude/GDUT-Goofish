package org.gdutgoodfish.goodfish.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author guazai
 * @date 2024-11-17 21:53:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {

    private Long id;

    private String userName;

    private Long itemId;

    private String context;

    private LocalDateTime createTime;

    private Long likes;
}
