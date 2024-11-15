package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.pojo.dto.MessageAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
public interface IMessageService extends IService<Message> {

    boolean addMessage(MessageAddDTO messageAddDTO);
}
