package org.gdutgoodfish.goodfish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.constant.PageConstant;
import org.gdutgoodfish.goodfish.exception.BaseException;
import org.gdutgoodfish.goodfish.mapper.CommentMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.CommentAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.CommentPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Comment;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.pojo.vo.CommentVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.ICommentService;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    IUsersService usersService;
    @Override
    public boolean addComment(CommentAddDTO commentAddDTO) {
        String context = commentAddDTO.getContext();
        Long itemId = commentAddDTO.getItemId();
        // 判断传入参数是否正确
        if (StringUtils.isBlank(context) || itemId == null) {
            throw new BaseException("评论和商品Id不能为空");
        }
        // 校验参数
        if (itemId < 0) {
            throw new BaseException("itemId不能小于0");
        }
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
    public PageQueryVO<CommentVO> pageQuery(CommentPageQueryDTO commentPageQueryDTO) {
        // 如果没传分页条件，用默认条件
        if (commentPageQueryDTO.getPage() == null) {
            commentPageQueryDTO.setPage(PageConstant.DEFAULT_PAGE);
        }
        if (commentPageQueryDTO.getPageSize() == null) {
            commentPageQueryDTO.setPageSize(PageConstant.DEFAULT_PAGE_SIZE);
        }
        // 参数校验
        if (commentPageQueryDTO.getPage() <= 0 || commentPageQueryDTO.getPageSize() <= 0) {
            throw new BaseException("page或pageSize参数错误");
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
        List<Comment> commentList = page.getRecords();
        List<CommentVO> commentVOList = commentList.stream().map(comment -> {
            // 不为空，复制到commentVO
            CommentVO commentVO = new CommentVO();
            BeanUtil.copyProperties(comment, commentVO);
            getUserName(comment, commentVO);
            return commentVO;
        }).collect(Collectors.toList());
        PageQueryVO<CommentVO> pageQueryVO = new PageQueryVO<>(page.getTotal(), commentVOList);
        return pageQueryVO;
    }

    private void getUserName(Comment comment, CommentVO commentVO) {
        // 获取用户
        Users user = usersService.getById(comment.getUserId());
        // 如果用户为空，将userName设置为用户已注销，否则设置userName
        commentVO.setUserName("用户已注销");
        commentVO.setUserName(user.getUsername());
    }

    @Override
    public List<Comment> getByItemId(Long itemId) {
        // 判断参数是否为空
        if (itemId == null) {
            throw new BaseException("商品id不能为空");
        }
        // 构建queryWrapper
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        // 查询得到Comment
        List<Comment> commentList = this.list(queryWrapper);
        // 返回
        return commentList;
    }
}
