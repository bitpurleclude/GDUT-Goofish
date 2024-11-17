package org.gdutgoodfish.goodfish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.mapper.MessageMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.MessageAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Message;
import org.gdutgoodfish.goodfish.pojo.vo.MessageVO;
import org.gdutgoodfish.goodfish.service.IMessageService;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Lazy
    @Autowired
    IUsersService usersService;

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
        message.setRead(isRead);
        // 存入message并返回结果
        return this.save(message);
    }

    @Override
    public boolean deleteAllMessage(Long receiveId) {
        // 获得userId（sendId）
        Long sendId = UserContext.getCurrentId();
        // 封装queryWrapper
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("send_id", sendId).or().eq("receive_id", sendId);
        // 删除消息并返回结果
        return this.remove(queryWrapper);
    }

    @Override
    public List<MessageVO> getAllMessage(Long receiveId) {
        // 构造queryWrapper
        Long sendId = UserContext.getCurrentId();
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("send_id", sendId)
                .eq("receive_id", receiveId)
                .or()
                .eq("send_id", receiveId)
                .eq("receive_id", sendId);
        // 查询聊天记录
        List<Message> messageList = this.list(queryWrapper);
        // 检查查询结果
        if (messageList == null || messageList.isEmpty()) {
            return new ArrayList<>();
        }
        // 将read设置为1
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("receive_id", receiveId);
        updateWrapper.set("`read`", 1);
        this.update(updateWrapper);
        // 将messageList转化为messageVOList
        List<MessageVO> messageVOList = messageList.stream().map(message -> {
            MessageVO messageVO = new MessageVO();
            BeanUtil.copyProperties(message, messageVO);
            setSendReceiveName(message, messageVO);
            return messageVO;
        }).collect(Collectors.toList());
        // 返回结果
        return messageVOList;
    }

    private void setSendReceiveName(Message message, MessageVO messageVO) {
        String sendUserName = usersService.getById(message.getSendId()).getUsername();
        String receiveUserName = usersService.getById(message.getReceiveId()).getUsername();
        messageVO.setSendUserName(sendUserName);
        messageVO.setReceiveUserName(receiveUserName);
    }
}
