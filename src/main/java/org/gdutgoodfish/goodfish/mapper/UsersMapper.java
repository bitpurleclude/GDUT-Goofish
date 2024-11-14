package org.gdutgoodfish.goodfish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.gdutgoodfish.goodfish.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.gdutgoodfish.goodfish.pojo.entity.Users;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    @Insert("INSERT INTO users (username, password, email, profile_picture, join_date) " +
            "VALUES (#{username}, #{password}, #{email}, #{profilePicture}, #{joinDate})")
    int insert(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM users WHERE userId = #{userId}")
    User findById(Long userId);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);

    @Update("UPDATE users SET password = #{newPassword} WHERE email = #{email}")
    int resetPasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);

}
