package org.gdutgoodfish.goodfish.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.bean.Favorites;
import org.gdutgoodfish.goodfish.bean.Items;
import org.gdutgoodfish.goodfish.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.mapper.FavoritesMapper;
import org.gdutgoodfish.goodfish.mapper.ItemsMapper;
import org.gdutgoodfish.goodfish.service.FavoritesService;
import org.gdutgoodfish.goodfish.vo.FavoritesPageQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesMapper favoritesMapper;

    private final ItemsMapper itemsMapper;

    @Override
    public void addFavority(FavoritesAddDTO favoritesAddDTO) {
        Favorites favorites = new Favorites();
        BeanUtils.copyProperties(favoritesAddDTO, favorites);
        // TODO 获取用户id
        Integer userId = 1;
        favorites.setUserId(userId);
        favoritesMapper.insertFavorites(favorites);
    }

    @Override
    public FavoritesPageQueryVO pageQuery(int page, int perSize) {
        // TODO 获取用户id
        Integer userId = 1;
        List<Integer> itemIds = favoritesMapper.selectItemIds(userId);
        PageHelper.startPage(page, perSize);
        Page<Items> pagedQuery = itemsMapper.pageQuery(itemIds);
        long total = pagedQuery.getTotal();
        List<Items> result = pagedQuery.getResult();
        return new FavoritesPageQueryVO(total, result);
    }

    @Override
    public void deleteFavorite(Integer itemId) {
        // TODO 获取用户id
        Integer userId = 1;
        Favorites favorites = new Favorites();
        favorites.setUserId(userId);
        favorites.setItemId(itemId);
        favoritesMapper.deleteById(favorites);
    }
}
