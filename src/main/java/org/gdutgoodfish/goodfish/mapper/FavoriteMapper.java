package org.gdutgoodfish.goodfish.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.gdutgoodfish.goodfish.pojo.entity.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;

import java.util.List;


/**
 * <p>
 * 收藏 Mapper 接口
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
public interface FavoriteMapper extends BaseMapper<Favorite> {

    IPage<ItemVO> pageQuery(IPage<ItemVO> page, @Param("itemIds") List<Long> itemIds);

}
