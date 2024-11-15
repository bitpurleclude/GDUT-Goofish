package org.gdutgoodfish.goodfish.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResetPasswordDTO {
    private String originPassword;
    private String newPassword;
}
