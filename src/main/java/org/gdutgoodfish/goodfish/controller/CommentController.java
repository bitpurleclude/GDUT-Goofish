package org.gdutgoodfish.goodfish.controller;


import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.CommentAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.CommentPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Comment;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 发布评价
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
        if(success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 获取评价详情
     * @param commentId
     * @return
     */
    @GetMapping("/getById/{commentId}")
    public Result<Comment> getById(@PathVariable("commentId") Long commentId) {
        // 校验参数
        if (commentId == null) {
            return Result.error("参数未传递");
        }
        // 获取评价
        Comment comment = commentService.getById(commentId);
        // 根据结果返回对应msg
        if(comment != null) {
            return Result.success(comment);
        } else {
            return Result.error("获取评论失败");
        }
    }


    /**
     * 分页查询用户评价
     * @param commentPageQueryDTO
     * @return
     */
    @GetMapping("/pageQuery")
    public Result<PageQueryVO<Comment>> CommentPageQuery(CommentPageQueryDTO commentPageQueryDTO) {
        log.info("用户评价分页条件查询 {}", commentPageQueryDTO);
        PageQueryVO<Comment> page = commentService.pageQuery(commentPageQueryDTO);
        return Result.success(page);
    }
}
