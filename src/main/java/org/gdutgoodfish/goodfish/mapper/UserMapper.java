package org.gdutgoodfish.goodfish.mapper;

import org.apache.ibatis.annotations.*;
import org.gdutgoodfish.goodfish.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, email, profile_picture, join_date) " +
            "VALUES (#{username}, #{password}, #{email}, #{profilePicture}, #{joinDate})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User findById(Long userId);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);

    @Update("UPDATE users SET password = #{newPassword} WHERE email = #{email}")
    int resetPasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);
}
