package org.gdutgoodfish.goodfish.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long user_id;

    private String username;

    private String password;

    private String email;

    private String profilePicture;

    private LocalDateTime joinDate;

    private String token;

    private String username_email;

    private String newpassword;
}
