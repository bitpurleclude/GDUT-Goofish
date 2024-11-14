package org.gdutgoodfish.goodfish.controller;

import org.gdutgoodfish.goodfish.dto.UserDTO;
import org.gdutgoodfish.goodfish.entity.Result;
import org.gdutgoodfish.goodfish.entity.User;
import org.gdutgoodfish.goodfish.service.UserService;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.util.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Result<UserDTO>> register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUser_id());
        user.setUsername(userDTO.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
        user.setEmail(userDTO.getEmail());
        user.setProfilePicture(userDTO.getProfilePicture());
        user.setJoinDate(userDTO.getJoinDate());

        User savedUser = userService.register(user);
        if (savedUser != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getUserId());
            claims.put("username", user.getUsername());
            claims.put("email", user.getEmail());
            String jwt = JwtUtil.generateJwt(claims);

            userDTO.setUser_id(savedUser.getUserId());
            userDTO.setPassword(null);
            userDTO.setToken(jwt);
            return new ResponseEntity<>(Result.success(userDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.error("用户名已存在"), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Result<UserDTO>> login(@RequestBody UserDTO userDTO) {
        String username_email = userDTO.getUsername_email();
        String password = userDTO.getPassword();
        User user = userService.login(username_email, password);

        // 登录成功，生成 token
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getUserId());
            claims.put("username", user.getUsername());
            claims.put("email", user.getEmail());

            String jwt = JwtUtil.generateJwt(claims);

            userDTO.setUser_id(user.getUserId());
            userDTO.setPassword(null);
            userDTO.setToken(jwt);
            return new ResponseEntity<>(Result.success(userDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.error("用户名或密码错误"), HttpStatus.OK);
        }
    }

    @PostMapping("/logout")
    public Result<String> logout(@RequestHeader String token) {
        // 把要登出的 token 加入黑名单
        JwtUtil.addToken(token);

        return Result.success("登出成功");
    }

    @PostMapping("/password-reset")
    public Result<String> resetPassword(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String newpassword = userDTO.getNewpassword();

        if (newpassword == null || newpassword.isEmpty()) {
            return Result.error("新密码不能为空");
        }
        if (userService.resetPassword(email, newpassword)) {
            return Result.success("密码重置成功");
        } else {
            return Result.error("邮箱不存在");
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Result<UserDTO>> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setProfilePicture(user.getProfilePicture());
            userDTO.setJoinDate(user.getJoinDate());

            return new ResponseEntity<>(Result.success(userDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.error("用户不存在"), HttpStatus.OK);
        }
    }

}


