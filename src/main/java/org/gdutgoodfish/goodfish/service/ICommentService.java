package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.pojo.dto.CommentAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.CommentPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;

import java.util.List;

/**
 * <p>
 * 评价表 服务类
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
public interface ICommentService extends IService<Comment> {

    boolean addComment(CommentAddDTO commentAddDTO);

    PageQueryVO<Comment> pageQuery(CommentPageQueryDTO commentPageQueryDTO);

    List<Comment> getByItemId(Long itemId);
}
