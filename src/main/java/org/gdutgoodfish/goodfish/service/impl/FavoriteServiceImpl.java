package org.gdutgoodfish.goodfish.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.mapper.FavoriteMapper;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Favorite;
import org.gdutgoodfish.goodfish.pojo.vo.FavoriteVO;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.IFavoriteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 收藏 服务实现类
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements IFavoriteService {

    @Override
    public Result addFavorite(FavoritesAddDTO favoritesAddDTO) {
        Favorite existingFavorite = lambdaQuery()
        .eq(Favorite::getItemId, favoritesAddDTO.getItemId())
        .eq(Favorite::getUserId, UserContext.getCurrentId())
        .one();

if (existingFavorite == null) {
    Favorite favorite = Favorite.builder()
            .itemId(favoritesAddDTO.getItemId())
            .userId(UserContext.getCurrentId())
            .createTime(LocalDateTime.now())
            .build();
    save(favorite);
    return Result.success("收藏成功");
}else {
    return Result.error("已收藏");
}
    }

    @Override
    public PageQueryVO<FavoriteVO> pageQuery(int page, int perSize) {
        List<Favorite> favorites = lambdaQuery().eq(Favorite::getUserId, UserContext.getCurrentId()).list();
        List<Long> itemIds = favorites.stream().map(Favorite::getItemId).toList();
        if (itemIds.isEmpty()) {
            return new PageQueryVO<>();
        }
        IPage<FavoriteVO> iPage = baseMapper.pageQuery(Page.of(page, perSize), UserContext.getCurrentId());
        return new PageQueryVO<>(iPage.getTotal(), iPage.getRecords());
    }

}
