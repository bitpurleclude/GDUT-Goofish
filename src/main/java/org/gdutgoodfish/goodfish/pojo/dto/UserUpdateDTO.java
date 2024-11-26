package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    /**
     * 用户名
     */
    @Pattern(regexp = "\\S+", message = "用户名不能包含空格")
    private String username;


    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 0未定义1男2女
     */
    private Integer sex;

    /**
     * 电话号码
     */
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "电话号码格式不正确")
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
