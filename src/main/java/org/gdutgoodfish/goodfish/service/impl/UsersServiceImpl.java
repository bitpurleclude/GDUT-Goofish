package org.gdutgoodfish.goodfish.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.exception.userException.UserExistException;
import org.gdutgoodfish.goodfish.exception.userException.UserLoginFailureException;
import org.gdutgoodfish.goodfish.exception.userException.UserResetPasswordException;
import org.gdutgoodfish.goodfish.mapper.UsersMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.UserLoginDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserRegisterDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {


    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        Users user = lambdaQuery().eq(Users::getUsername, userRegisterDTO.getUsername()).one();
        if (user != null) {
            throw new UserExistException("用户名已存在");
        }
        user = Users.builder()
                .username(userRegisterDTO.getUsername())
                .password(userRegisterDTO.getPassword())
                .creatTime(LocalDateTime.now()).build();
        save(user);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        Users user = lambdaQuery().eq(Users::getUsername, userLoginDTO.getUsername())
                .eq(Users::getPassword, userLoginDTO.getPassword()).one();
        if (user == null) {
            throw new UserLoginFailureException("用户登陆失败");
        }
        String userJson = JSONObject.toJSONString(user);
        return JwtUtil.createJWT(userJson);
    }

    @Override
    public void logout(Long userId) {
        // TODO
    }

    @Transactional
    @Override
    public void resetPassword(UserResetPasswordDTO userResetPasswordDTO) {
        Users users = lambdaQuery().eq(Users::getId, UserContext.getCurrentId())
                .eq(Users::getPassword, userResetPasswordDTO.getOriginPassword())
                .one();
        if (users == null) {
            throw new UserResetPasswordException("原密码错误");
        }
        lambdaUpdate().eq(Users::getId, users.getId())
                .set(Users::getPassword, userResetPasswordDTO.getNewPassword())
                .update();
    }

}
