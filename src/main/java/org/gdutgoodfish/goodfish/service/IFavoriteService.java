package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.pojo.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Favorite;
import com.baomidou.mybatisplus.extension.service.IService;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.vo.FavoriteVO;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;

/**
 * <p>
 * 收藏 服务类
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
public interface IFavoriteService extends IService<Favorite> {
    void addFavorite(FavoritesAddDTO favoritesAddDTO);

    PageQueryVO<FavoriteVO> pageQuery(int page, int perSize);


}
