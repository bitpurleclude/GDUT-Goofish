package org.gdutgoodfish.goodfish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User { // 用户实体类
    private Long userId;         // 用户ID或主键

    private String username;     // 用户名

    private String password;     // 密码

    private String email;        // 邮箱

    private String profilePicture;       // 用户头像的 URL

    private LocalDateTime joinDate;     // 创建时间

}
