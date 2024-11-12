package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.entity.User;
public interface UserService {
    boolean register(User user);

    User login(String identifier, String password);

    boolean logout(Long userId);

    boolean resetPassword(String email, String newPassword);

    User getUserById(Long userId);

    boolean insertUser(User user);
}
