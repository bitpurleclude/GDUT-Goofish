package org.gdutgoodfish.goodfish.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.pojo.vo.FavoriteVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.IFavoriteService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
@Slf4j
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @PostMapping
    public Result addFavorite(@RequestBody FavoritesAddDTO favoritesAddDTO) {
        log.info("收藏商品id {}", favoritesAddDTO.getItemId());
        return favoriteService.addFavorite(favoritesAddDTO);
    }

    @GetMapping
    public Result<PageQueryVO<FavoriteVO>> pageQueryFavorite(@RequestParam("page") int page,
                                                             @RequestParam("perSize") int perSize) {
        log.info("收藏分页查询{} {}", page, perSize);
        PageQueryVO<FavoriteVO> favoritesPageQueryVO = favoriteService.pageQuery(page, perSize);
        return Result.success(favoritesPageQueryVO);
    }

    @DeleteMapping("/{favoriteId}")
    public Result<String> deleteFavorite(@PathVariable Integer favoriteId) {
        log.info("删除收藏id {}", favoriteId);
        favoriteService.removeById(favoriteId);
        return Result.success("删除收藏成功");
    }

}
