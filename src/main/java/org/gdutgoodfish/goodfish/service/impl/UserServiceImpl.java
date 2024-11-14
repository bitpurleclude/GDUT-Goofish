package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.entity.User;
import org.gdutgoodfish.goodfish.mapper.UserMapper;
import org.gdutgoodfish.goodfish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return null;
        }
        userMapper.insert(user);
        return user;
    }

    @Override
    public User login(String username_email, String password) {
        User user = userMapper.findByUsername(username_email);
        if (user == null) {
            user = userMapper.findByEmail(username_email);
        }
        if (user != null && user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return user;
        }
        return null;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        return userMapper.resetPasswordByEmail(email, DigestUtils.md5DigestAsHex(newPassword.getBytes())) > 0;
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    public boolean insertUser(User user) {
        int result = userMapper.insert(user);
        return result > 0;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
