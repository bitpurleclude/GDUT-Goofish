package org.gdutgoodfish.goodfish.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.MessageAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Message;
import org.gdutgoodfish.goodfish.pojo.vo.MessageVO;
import org.gdutgoodfish.goodfish.pojo.vo.UserVO;
import org.gdutgoodfish.goodfish.service.IMessageService;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    IMessageService messageService;

    @Autowired
    IUsersService usersService;

    /**
     * 新增消息
     *
     * @param messageAddDTO
     * @return
     */
    @PostMapping("/add")
    public Result addMessage(@RequestBody MessageAddDTO messageAddDTO) {
        log.info("添加消息：{}", messageAddDTO);
        // 存入message
        boolean success = messageService.addMessage(messageAddDTO);
        // 返回结果
        if (success) {
            return Result.success("发送成功");
        } else {
            return Result.error("发送失败");
        }
    }

    /**
     * 删除与某个人某条记录
     */
    @DeleteMapping("/deleteById/{messageId}")
    public Result deleteMessage(@PathVariable("messageId") Long messageId) {
        log.info("要删除的messageId为：{}", messageId);
        // 判断传入参数是否为空
        if (messageId == null) {
            return Result.error("传入参数为空");
        }
        // 删除message
        boolean success = messageService.removeById(messageId);
        // 返回结果
        if (success) {
            return Result.success("删除message成功");
        } else {
            return Result.error("删除message失败");
        }
    }

    /**
     * 清空与某个人的聊天记录
     *
     * @param receiveId
     * @return
     */
    @DeleteMapping("/deleteAll")
    public Result deleteAllMessage(@RequestParam("receiveId") Long receiveId) {
        log.info("清空和receiveId为{}的聊天记录", receiveId);
        // 判断传入参数是否为空
        if (receiveId == null) {
            return Result.error("传入参数为空");
        }
        // 删除message
        boolean success = messageService.deleteAllMessage(receiveId);
        // 返回结果
        if (success) {
            return Result.success("清空聊天记录成功");
        } else {
            return Result.error("清空聊天记录失败");
        }
    }

    /**
     * 获取单条消息
     *
     * @param messageId
     * @return
     */
    @GetMapping("/getById/{messageId}")
    public Result<MessageVO> getById(@PathVariable("messageId") Long messageId) {
        // 校验参数
        if (messageId == null) {
            return Result.error("参数未传递");
        }
        // 获取message
        Message message = messageService.getById(messageId);
        if (message == null) {
            return Result.error("获取消息失败");
        }
        MessageVO messageVO = new MessageVO();
        BeanUtil.copyProperties(message, messageVO);
        setSendReceiveName(message, messageVO);
        // 根据结果返回对应msg
        // 标记已读
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", messageId);
        updateWrapper.set("`read`", 1);
        messageService.update(updateWrapper);
        message.setRead(1);
        // 返回结果
        return Result.success(messageVO);
    }

    private void setSendReceiveName(Message message, MessageVO messageVO) {
        String sendUserName = usersService.getById(message.getSendId()).getUsername();
        String receiveUserName = usersService.getById(message.getReceiveId()).getUsername();
        messageVO.setSendUserName(sendUserName);
        messageVO.setReceiveUserName(receiveUserName);
    }

    /**
     * 查询与某人的全部聊天记录
     *
     * @param receiveId
     * @return
     */
    @GetMapping("/getAllMessage")
    public Result<List<MessageVO>> getAllMessage(@RequestParam("receiveId") Long receiveId) {
        log.info("查询与receiveId为{}的全部聊天记录", receiveId);
        // 如果传入参数为空，返回错误
        if (receiveId == null) {
            return Result.error("传入参数为空");
        }
        // 查询
        List<MessageVO> messageVOList = messageService.getAllMessage(receiveId);
        return Result.success(messageVOList);
    }

    /**
     * 获得跟自己聊过天的人的集合
     */
    @GetMapping("/getChattedUsers")
    public Result<List<UserVO>> getChattedUsers() {
        log.info("查询与当前用户为聊过天的人");
        // 查询
        List<UserVO> userVOList = usersService.getChattedUsers();
        if (userVOList == null || userVOList.isEmpty()) {
            userVOList = new ArrayList<>();
        }
        return Result.success(userVOList);
    }

}
