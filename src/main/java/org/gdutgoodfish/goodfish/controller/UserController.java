package org.gdutgoodfish.goodfish.controller;


import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.entity.Result;
import org.gdutgoodfish.goodfish.entity.User;
import org.gdutgoodfish.goodfish.service.UserService;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping ("/api/v1/auth")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        if (userService.register(user)) {
            return Result.success("用户注册成功");
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody Map<String, String> loginRequest) {
        Iterator<Map.Entry<String, String>> iterator = loginRequest.entrySet().iterator();
        Map.Entry<String, String> firstEntry = iterator.next();
        String identifier = firstEntry.getValue(); // 第一个参数

        Map.Entry<String, String> secondEntry = iterator.next();
        String password = secondEntry.getValue(); // 第二个参数
        User user = userService.login(identifier, password);

        // 登录成功，生成 token
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            claims.put("username", user.getUsername());
            claims.put("email", user.getEmail());

            String jwt = JwtUtil.generateJwt(claims);

            return Result.success(jwt);
        } else {
            return Result.error("用户名或邮箱错误或者密码错误");
        }
    }


    @PostMapping("/logout")
    public Result<String> logout(@RequestBody Map<String, Long> logoutRequest) {
        Long userId = logoutRequest.get("userId");
        // 执行登出逻辑
        return Result.success("登出成功");
    }

    @PostMapping("/password-reset")
    public Result<String> resetPassword(@RequestBody Map<String, String> resetRequest) {
        Iterator<Map.Entry<String, String>> iterator = resetRequest.entrySet().iterator();
        Map.Entry<String, String> firstEntry = iterator.next();
        String email = firstEntry.getValue(); // 第一个参数

        Map.Entry<String, String> secondEntry = iterator.next();
        String newPassword = secondEntry.getValue(); // 第二个参数
        if (newPassword == null || newPassword.isEmpty()) {
            return Result.error("新密码不能为空");
        }
        if (userService.resetPassword(email, newPassword)) {
            return Result.success("密码重置成功");
        } else {
            return Result.error("邮箱不存在");
        }
    }

    @GetMapping("/users/{userId}")
    public Result<User> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("用户不存在");
        }
    }

}


