package org.gdutgoodfish.goodfish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.exception.userException.UserExistException;
import org.gdutgoodfish.goodfish.exception.userException.UserLoginFailureException;
import org.gdutgoodfish.goodfish.exception.userException.UserResetPasswordException;
import org.gdutgoodfish.goodfish.mapper.UsersMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.UserLoginDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserRegisterDTO;
import org.gdutgoodfish.goodfish.pojo.dto.UserResetPasswordDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Message;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.pojo.vo.UserVO;
import org.gdutgoodfish.goodfish.service.IMessageService;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Lazy
    @Autowired
    IMessageService messageService;

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        Users user = lambdaQuery().eq(Users::getUsername, userRegisterDTO.getUsername()).one();
        if (user != null) {
            throw new UserExistException("用户名已存在");
        }
        String password = DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes());
        user = Users.builder()
                .username(userRegisterDTO.getUsername())
                .password(password)
                .creatTime(LocalDateTime.now()).build();
        save(user);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        String password = DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes());
        Users user = lambdaQuery().eq(Users::getUsername, userLoginDTO.getUsername())
                .eq(Users::getPassword, password).one();
        if (user == null) {
            throw new UserLoginFailureException("用户登陆失败");
        }
        String userJson = JSONObject.toJSONString(user);
        return JwtUtil.createJWT(userJson);
    }

    @Override
    public void logout(Long userId) {
        // TODO
    }

    @Transactional
    @Override
    public void resetPassword(UserResetPasswordDTO userResetPasswordDTO) {
        Users users = lambdaQuery().eq(Users::getId, UserContext.getCurrentId())
                .eq(Users::getPassword, userResetPasswordDTO.getOriginPassword())
                .one();
        if (users == null) {
            throw new UserResetPasswordException("原密码错误");
        }
        String password = DigestUtils.md5DigestAsHex(userResetPasswordDTO.getNewPassword().getBytes());
        lambdaUpdate().eq(Users::getId, users.getId())
                .set(Users::getPassword, password)
                .update();
    }

    @Override
    public List<UserVO> getChattedUsers() {
        // 获得当前用户id
        Long userId = UserContext.getCurrentId();
        // 从message表中获得sendId为用户id的消息中的receiveId并去重
        QueryWrapper<Message> messageQueryWrapper1 = new QueryWrapper<>();
        messageQueryWrapper1.select("distinct receive_id").eq("send_id", userId);
        List<Message> messageList1 = messageService.list(messageQueryWrapper1);
        // 从message表中获得receiveId为用户id的消息中的sendId并去重
        QueryWrapper<Message> messageQueryWrapper2 = new QueryWrapper<>();
        messageQueryWrapper2.select("distinct send_Id").eq("receive_id", userId);
        List<Message> messageList2 = messageService.list(messageQueryWrapper2);
        // 将两个id集合合并到set集合中
        Set<Long> idSet = new HashSet<>();
        // 查询id集合对应userList
        for (Message message : messageList1) {
            idSet.add(message.getReceiveId());
        }

        for (Message message : messageList2) {
            idSet.add(message.getSendId());
        }
        // 如果idSet为空，返回空list
        if (idSet.isEmpty()) {
            return new ArrayList<>();
        }
        // 查询user
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idSet);
        List<Users> userList = this.list(queryWrapper);
        // 如果userList集合为空，返回空list
        if (userList == null || userList.isEmpty()) {
            return new ArrayList<>();
        }
        // 将userList转化成userVOList
        List<UserVO> userVOList = userList.stream().map(user -> BeanUtil.copyProperties(user, UserVO.class)).collect(Collectors.toList());
        return userVOList;
    }

}
