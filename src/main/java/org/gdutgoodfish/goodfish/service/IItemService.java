package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.pojo.dto.ItemAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
public interface IItemService extends IService<Item> {
    ItemVO getItem(Long itemId);

    PageQueryVO<ItemVO> pageQuery(ItemPageQueryDTO itemPageQueryDTO);

}
