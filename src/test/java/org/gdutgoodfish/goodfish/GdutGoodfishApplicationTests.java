package org.gdutgoodfish.goodfish;

import org.gdutgoodfish.goodfish.entity.User;
import org.gdutgoodfish.goodfish.controller.UserController;
import org.gdutgoodfish.goodfish.entity.Result;
import org.gdutgoodfish.goodfish.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
class GdutGoodfishApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserController userController;

    @Test
    void testInsertUser() {
        User user = new User();
        user.setUsername("testuser3");
        user.setPassword("password3");
        user.setEmail("testuser3@example.com");
        user.setProfilePicture("https://example.com/testuser1.jpg");
        user.setJoinDate(LocalDateTime.now());

        Result result = userController.register(user);
        System.out.println(result);
    }

    @Test
    void testResetPassword() {
        String email = "testuser@example.com";
        String newPassword = "aaa1234";
        Map<String, String> resetRequest = Map.of("email", email, "newPassword", newPassword);

        Result result = userController.resetPassword(resetRequest);
        System.out.println(result);
    }

    @Test
    void testLogin() {
        String email = "testuser1@example.com";
        String password = "password1";
        Map<String, String> loginRequest = Map.of("identifier", email, "password", password);

        Result result = userController.login(loginRequest);
        System.out.println(result);
    }

    @Test
    void testLogout() {
        Long username = 1L;
        Map<String, Long> logoutRequest = Map.of("userId", username);

        Result result = userController.logout(logoutRequest);
        System.out.println(result);
    }

    @Test
    void contextLoads() {
    }

}
