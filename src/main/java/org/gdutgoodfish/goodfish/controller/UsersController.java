package org.gdutgoodfish.goodfish.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.UserLoginDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserRegisterDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserUpdateDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.pojo.vo.UserVO;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.gdutgoodfish.goodfish.util.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping ("/users")
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final IUsersService usersService;
    private final TokenUtils tokenUtils;

    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("register user: {}", userRegisterDTO);
        usersService.register(userRegisterDTO);
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public Result<Map<String, String>> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        log.info("login user: {}", userLoginDTO);
        String token = usersService.login(userLoginDTO);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return Result.success(map, "登陆成功");
    }


    @PutMapping("/logout")
    public Result<String> logout(@RequestHeader("token") String token) {
        log.info("logout user{}", UserContext.getCurrentId());
        tokenUtils.invalidateToken(token);
        return Result.success("登出成功");
    }

    @PutMapping("/resetPassword")
    public Result<String> resetPassword(@Valid @RequestBody UserResetPasswordDTO resetPasswordDTO) {
        log.info("resetPassword user: {}", resetPasswordDTO);
        usersService.resetPassword(resetPasswordDTO);
        return Result.success("密码重设成功");
    }

    @GetMapping
    public Result<UserVO> getUserProfile() {
        log.info("get user profile");
        Users user = usersService.getById(UserContext.getCurrentId());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    @PutMapping("/update")
    public Result<String> update(@Valid UserUpdateDTO userUpdateDTO) {
        log.info("update user: {}", userUpdateDTO);
        Users user = new Users();
        BeanUtils.copyProperties(userUpdateDTO, user);
        user.setId(UserContext.getCurrentId());
        boolean update = usersService.update(user);
        if (update) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }

    }

}


