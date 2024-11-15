package org.gdutgoodfish.goodfish.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
public interface ItemMapper extends BaseMapper<Item> {

    IPage<ItemVO> pageQuery(IPage<ItemVO> page, @Param("dto") ItemPageQueryDTO dto);

    List<ItemVO> selectUserItems(Long userId);
}
