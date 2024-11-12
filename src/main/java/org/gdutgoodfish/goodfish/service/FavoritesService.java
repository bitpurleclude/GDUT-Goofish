package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.vo.FavoritesPageQueryVO;

public interface FavoritesService {
    void addFavority(FavoritesAddDTO favoritesAddDTO);

    FavoritesPageQueryVO pageQuery(int page, int perSize);

    void deleteFavorite(Integer itemId);
}
