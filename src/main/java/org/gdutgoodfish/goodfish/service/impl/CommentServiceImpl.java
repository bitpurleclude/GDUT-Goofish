package org.gdutgoodfish.goodfish.service.impl;

import org.gdutgoodfish.goodfish.pojo.entity.Comment;
import org.gdutgoodfish.goodfish.mapper.CommentMapper;
import org.gdutgoodfish.goodfish.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评价表 服务实现类
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
