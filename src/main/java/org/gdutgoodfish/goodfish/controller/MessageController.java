package org.gdutgoodfish.goodfish.controller;


import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.MessageAddDTO;
import org.gdutgoodfish.goodfish.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
