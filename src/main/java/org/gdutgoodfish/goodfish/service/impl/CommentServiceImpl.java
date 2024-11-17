package org.gdutgoodfish.goodfish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.constant.PageConstant;
import org.gdutgoodfish.goodfish.mapper.CommentMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.CommentAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.CommentPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Comment;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.ICommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean addComment(CommentAddDTO commentAddDTO) {
        // 获取传入的信息
        Comment comment = new Comment();
        BeanUtil.copyProperties(commentAddDTO, comment);
        Long userId = UserContext.getCurrentId();
        // 设置userId和CreateTime
        comment.setUserId(userId);
        comment.setCreateTime(LocalDateTime.now());
        // 执行添加并返回结果
        return save(comment);
    }

    @Override
    public PageQueryVO<Comment> pageQuery(CommentPageQueryDTO commentPageQueryDTO) {
        // 如果没传分页条件，用默认条件
        if (commentPageQueryDTO.getPage() == null) {
            commentPageQueryDTO.setPage(PageConstant.DEFAULT_PAGE);
        }
        if (commentPageQueryDTO.getPageSize() == null) {
            commentPageQueryDTO.setPageSize(PageConstant.DEFAULT_PAGE_SIZE);
        }
        // 创建分页对象
        Page<Comment> page = new Page<>(commentPageQueryDTO.getPage(), commentPageQueryDTO.getPageSize());
        // 创建 QueryWrapper 对象，动态构建查询条件
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        // 根据条件动态添加查询条件
        Long userId = commentPageQueryDTO.getUserId();
        Long itemId = commentPageQueryDTO.getItemId();
        String context = commentPageQueryDTO.getContext();
        queryWrapper.eq(userId != null, "user_id", userId);
        queryWrapper.eq(itemId != null, "item_id", itemId);
        queryWrapper.like(StringUtils.isNotEmpty(context), "context", context);
        // 执行分页查询
        page = this.baseMapper.selectPage(page, queryWrapper);
        // 查看返回结果
        if (page.getRecords() == null || page.getRecords().isEmpty()) {
            return new PageQueryVO<>(0L, new ArrayList<>());
        }
        //返回结果
        PageQueryVO<Comment> pageQueryVO = new PageQueryVO<>(page.getTotal(), page.getRecords());
        return pageQueryVO;
    }

    @Override
    public List<Comment> getByItemId(Long itemId) {
        // 构建queryWrapper
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        // 查询得到Comment
        List<Comment> commentList = this.list(queryWrapper);
        // 返回
        return commentList;
    }
}
