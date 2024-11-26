package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResetPasswordDTO {
    @NotBlank(message = "原密码不能为空且不能包含空格")
    @Pattern(regexp = "\\S+", message = "密码不能包含空格")
    private String originPassword;
    @NotBlank(message = "密码不能为空且不能包含空格")
    @Pattern(regexp = "\\S+", message = "密码不能包含空格")
    private String newPassword;
}
