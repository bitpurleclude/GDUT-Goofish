package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.entity.User;
import org.gdutgoodfish.goodfish.mapper.UserMapper;
import org.gdutgoodfish.goodfish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false;
        }
        return userMapper.insert(user) > 0;
    }

    @Override
    public User login(String identifier, String password) {
        User user = userMapper.findByUsername(identifier);
        if (user == null) {
            user = userMapper.findByEmail(identifier);
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean logout(Long userId) {
        return true; // 简单实现，实际逻辑可能需要处理会话
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        return userMapper.resetPasswordByEmail(email, newPassword) > 0;
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
}
