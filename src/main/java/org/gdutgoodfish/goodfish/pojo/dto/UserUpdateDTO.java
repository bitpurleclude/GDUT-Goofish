package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    /**
     * 用户名
     */
    private String username;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 0未定义1男2女
     */
    private Integer sex;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime creatTime;
}
