package org.gdutgoodfish.goodfish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.mapper.MessageMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.MessageAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Message;
import org.gdutgoodfish.goodfish.service.IMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Override
    public boolean addMessage(MessageAddDTO messageAddDTO) {
        // 从messageAddDTO取出参数复制到message实体
        Message message = BeanUtil.copyProperties(messageAddDTO, Message.class);
        // 往message添加默认值
        Long sendId = UserContext.getCurrentId();
        LocalDateTime createTime = LocalDateTime.now();
        Integer isRead = 0;
        message.setSendId(sendId);
        message.setCreateTime(createTime);
        message.setIsRead(isRead);
        // 存入message并返回结果
        return this.save(message);
    }


}
