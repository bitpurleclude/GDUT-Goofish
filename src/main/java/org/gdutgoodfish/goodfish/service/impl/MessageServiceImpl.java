package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.pojo.entity.Message;
import org.gdutgoodfish.goodfish.mapper.MessageMapper;
import org.gdutgoodfish.goodfish.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
