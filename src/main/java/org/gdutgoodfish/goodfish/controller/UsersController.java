package org.gdutgoodfish.goodfish.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping ("/users")
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody Users users) {
        log.info("register user: {}", users);
        usersService.register(users);
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody Users users) {
        log.info("login user: {}", users);
        String token = usersService.login(users);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return Result.success(map);
    }


    @PutMapping("/logout")
    public Result<String> logout() {
        log.info("logout user{}", UserContext.getCurrentId());
        // TODO 执行登出逻辑
        return Result.success("登出成功");
    }

    @PutMapping("/resetPassword")
    public Result<String> resetPassword(@RequestBody UserResetPasswordDTO resetPasswordDTO) {
        log.info("resetPassword user: {}", resetPasswordDTO);
        usersService.resetPassword(resetPasswordDTO);
        return Result.success("密码重设成功");
    }

    @GetMapping
    public Result<Users> getUserProfile() {
        Users user = usersService.getById(UserContext.getCurrentId());
        return Result.success(user);
    }

}


