package org.gdutgoodfish.goodfish.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.MessageAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Message;
import org.gdutgoodfish.goodfish.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增消息
     * @param messageAddDTO
     * @return
     */
    @PostMapping("/add")
    public Result addMessage(@RequestBody MessageAddDTO messageAddDTO) {
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
     * @param messageId
     * @return
     */
    @GetMapping("/getById/{messageId}")
    public Result<Message> getById(@PathVariable("messageId") Long messageId) {
        // 校验参数
        if (messageId == null) {
            return Result.error("参数未传递");
        }
        // 获取message
        Message message = messageService.getById(messageId);
        // 根据结果返回对应msg
        if(message != null) {
            // 标记已读
            UpdateWrapper updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", messageId);
            updateWrapper.set("`read`", 1);
            messageService.update(updateWrapper);
            message.setRead(1);
            // 返回结果
            return Result.success(message);
        } else {
            return Result.error("获取消息失败");
        }
    }

    /**
     * 查询与某人的全部聊天记录
     * @param receiveId
     * @return
     */
    @GetMapping("/getAllMessage")
    public Result<List<Message>> getAllMessage(@RequestParam("receiveId") Long receiveId) {
        log.info("查询与receiveId为{}的全部聊天记录", receiveId);
        // 如果传入参数为空，返回错误
        if (receiveId == null) {
            return Result.error("传入参数为空");
        }
        // 查询
        List<Message> messageList = messageService.getAllMessage(receiveId);
        return Result.success(messageList);
    }
}
