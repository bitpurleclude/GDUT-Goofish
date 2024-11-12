package org.gdutgoodfish.goodfish.controller;


import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.bean.Result;
import org.gdutgoodfish.goodfish.dto.FavoritesAddDTO;
import org.gdutgoodfish.goodfish.service.FavoritesService;
import org.gdutgoodfish.goodfish.vo.FavoritesPageQueryVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-12
 */
@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    /**
     * 添加收藏
     * @param favoritesAddDTO
     * @return
     */
    @PutMapping
    public Result addFavorite(@RequestBody FavoritesAddDTO favoritesAddDTO) {
        favoritesService.addFavority(favoritesAddDTO);
        return Result.success("收藏添加成功");
    }

    @GetMapping
    public Result<FavoritesPageQueryVO> pageQueryFavorite(@RequestParam int page, @RequestParam int perSize) {
        FavoritesPageQueryVO favoritesPageQueryVO = favoritesService.pageQuery(page, perSize);
        return Result.success(favoritesPageQueryVO);
    }

    @DeleteMapping("/{itemId}")
    public Result deleteFavorite(@PathVariable Integer itemId) {
        favoritesService.deleteFavorite(itemId);
        return Result.success("删除收藏成功");
    }

}
