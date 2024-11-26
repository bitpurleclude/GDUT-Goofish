package org.gdutgoodfish.goodfish.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    @NotBlank(message = "用户名不能为空且不能包含空格")
    @Pattern(regexp = "\\S+", message = "用户名不能包含空格")
    private String username;
    @NotBlank(message = "密码不能为空且不能包含空格")
    @Pattern(regexp = "\\S+", message = "密码不能包含空格")
    private String password;
}
