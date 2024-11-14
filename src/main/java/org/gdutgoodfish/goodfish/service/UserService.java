package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.entity.User;
public interface UserService {
    boolean register(User user);

    User login(String identifier, String password);

//    boolean logout(Integer userId);

    boolean resetPassword(String email, String newPassword);

    User getUserById(Integer userId);

    boolean insertUser(User user);

    User getUserByUsername(String username);
}
