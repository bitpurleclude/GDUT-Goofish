package org.gdutgoodfish.goodfish.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.gdutgoodfish.goodfish.pojo.dto.UserLoginDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserRegisterDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Users;

public interface IUsersService extends IService<Users> {
    void register(UserRegisterDTO registerDTO);

    String login(UserLoginDTO userLoginDTO);

    void logout(Long userId);

    void resetPassword(UserResetPasswordDTO resetPasswordDTO);

}
