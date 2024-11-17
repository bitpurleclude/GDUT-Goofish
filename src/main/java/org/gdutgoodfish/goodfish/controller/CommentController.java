package org.gdutgoodfish.goodfish.controller;


import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.CommentAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.CommentPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Comment;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.pojo.vo.CommentVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.ICommentService;
import org.gdutgoodfish.goodfish.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评价表 前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    ICommentService commentService;

    @Autowired
    IUsersService usersService;

    /**
     * 发布评价
     *
     * @param commentAddDTO
     * @return
     */
    @PostMapping("/add")
    public Result addComment(@RequestBody CommentAddDTO commentAddDTO) {
        if (commentAddDTO == null) {
            return Result.error("参数未传递");
        }
        boolean success = commentService.addComment(commentAddDTO);
        if (success) {
            return Result.success("发布成功");
        } else {
            return Result.error("发布失败");
        }
    }

    /**
     * 删除评价
     *
     * @param commentId
     * @return
     */
    @DeleteMapping("/deleteById/{commentId}")
    public Result deleteById(@PathVariable("commentId") Long commentId) {
        // 校验参数
        if (commentId == null) {
            return Result.error("参数未传递");
        }
        // 删除，获取删除结果
        boolean success = commentService.removeById(commentId);
        // 根据结果返回对应msg
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 获取评价详情
     *
     * @param commentId
     * @return
     */
    @GetMapping("/getById/{commentId}")
    public Result<CommentVO> getById(@PathVariable("commentId") Long commentId) {
        // 校验参数
        if (commentId == null) {
            return Result.error("参数未传递");
        }
        // 获取评价
        Comment comment = commentService.getById(commentId);
        // 如果评价为空，返回错误
        if (comment == null) {
            return Result.error("获取评论失败");
        }
        // 不为空，复制到commentVO
        CommentVO commentVO = new CommentVO();
        BeanUtil.copyProperties(comment, commentVO);
        getUserName(comment, commentVO);
        // 根据结果返回对应msg
        return Result.success(commentVO);
    }

    private void getUserName(Comment comment, CommentVO commentVO) {
        // 获取用户
        Users user = usersService.getById(comment.getUserId());
        // 如果用户为空，将userName设置为用户已注销，否则设置userName
        commentVO.setUserName("用户已注销");
        commentVO.setUserName(user.getUsername());
    }

    @GetMapping("/getByItemId")
    public Result<List<CommentVO>> getByItemId(@RequestParam("itemId") Long itemId) {
        // 校验参数
        if (itemId == null) {
            return Result.error("参数未传递");
        }
        // 获取评价
        List<Comment> commentList = commentService.getByItemId(itemId);
        if (commentList == null || commentList.isEmpty()) {
            return Result.success(new ArrayList<>());
        }

        List<CommentVO> commentVOList = commentList.stream().map(comment -> {
            // 不为空，复制到commentVO
            CommentVO commentVO = new CommentVO();
            BeanUtil.copyProperties(comment, commentVO);
            getUserName(comment, commentVO);
            return commentVO;
        }).collect(Collectors.toList());
        // 返回
        return Result.success(commentVOList);
    }


    /**
     * 分页查询用户评价
     *
     * @param commentPageQueryDTO
     * @return
     */
    @GetMapping("/pageQuery")
    public Result<PageQueryVO<CommentVO>> CommentPageQuery(CommentPageQueryDTO commentPageQueryDTO) {
        log.info("用户评价分页条件查询 {}", commentPageQueryDTO);
        PageQueryVO<CommentVO> page = commentService.pageQuery(commentPageQueryDTO);
        return Result.success(page);
    }

}
