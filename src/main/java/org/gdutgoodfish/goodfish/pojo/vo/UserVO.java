package org.gdutgoodfish.goodfish.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    private Long id;

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
