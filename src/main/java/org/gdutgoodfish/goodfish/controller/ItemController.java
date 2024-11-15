package org.gdutgoodfish.goodfish.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.ItemAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.IItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final IItemService itemService;


    @DeleteMapping("/{itemId}")
    public Result<String> deleteItem(@PathVariable("itemId") Integer itemId) {
        log.info("删除商品: {}", itemId);
        itemService.removeById(itemId);
        return Result.success("商品删除成功");
    }

    @PostMapping
    public Result<String> addItems(@RequestBody ItemAddDTO itemAddDTO) {
        log.info("添加商品: {}", itemAddDTO);
        Item item = new Item();
        BeanUtils.copyProperties(itemAddDTO, item);
        item.setCreateTime(LocalDateTime.now());
        item.setUserId(UserContext.getCurrentId());
        itemService.save(item);
        return Result.success("商品添加成功");
    }

    @GetMapping("/{itemId}")
    public Result<ItemVO> getItem(@PathVariable("itemId") Long itemId) {
        log.info("获取商品 {}", itemId);
        ItemVO itemVO = itemService.getItem(itemId);
        return Result.success(itemVO);
    }

    @GetMapping("/pageQuery")
    public Result<PageQueryVO<ItemVO>> getItemPageQuery(@RequestBody ItemPageQueryDTO itemPageQueryDTO) {
        log.info("商品分页条件查询 {}", itemPageQueryDTO);
        PageQueryVO<ItemVO> pageVO = itemService.pageQuery(itemPageQueryDTO);
        return Result.success(pageVO);
    }





}
