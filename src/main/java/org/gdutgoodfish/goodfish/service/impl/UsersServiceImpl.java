package org.gdutgoodfish.goodfish.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.exception.userException.UserExistException;
import org.gdutgoodfish.goodfish.exception.userException.UserLoginFailureException;
import org.gdutgoodfish.goodfish.exception.userException.UserResetPasswordException;
import org.gdutgoodfish.goodfish.mapper.UsersMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.service.UsersService;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {


    @Override
    public void register(Users users) {
        Users user = lambdaQuery().eq(Users::getUsername, users.getUsername()).one();
        if (user != null) {
            throw new UserExistException("用户名已存在");
        }
        users.setCreatTime(LocalDateTime.now());
        save(users);
    }

    @Override
    public String login(Users loginUser) {
        Users user = lambdaQuery().eq(Users::getUsername, loginUser.getUsername())
                .eq(Users::getPassword, loginUser.getPassword()).one();
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
