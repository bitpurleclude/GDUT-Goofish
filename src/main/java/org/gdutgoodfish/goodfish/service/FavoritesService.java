package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.pojo.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.pojo.vo.FavoritesPageQueryVO;

public interface FavoritesService {
    void addFavority(FavoritesAddDTO favoritesAddDTO);

    FavoritesPageQueryVO pageQuery(int page, int perSize);

    void deleteFavorite(Integer itemId);
}
