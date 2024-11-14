package org.gdutgoodfish.goodfish.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Users;

public interface UsersService extends IService<Users> {
    void register(Users users);

    String login(Users users);

    void logout(Long userId);

    void resetPassword(UserResetPasswordDTO resetPasswordDTO);

}
