package org.gdutgoodfish.goodfish.controller;


import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.ItemAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.dto.ItemUpdateDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
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


    @DeleteMapping
    public Result<String> deleteItem(@RequestParam("ids") List<Long> ids) {
        log.info("删除商品: {}", ids);
        itemService.removeByIds(ids);
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
    public Result<PageQueryVO<ItemVO>> getItemPageQuery(ItemPageQueryDTO itemPageQueryDTO) {
        log.info("商品分页条件查询 {}", itemPageQueryDTO);
        PageQueryVO<ItemVO> pageVO = itemService.pageQuery(itemPageQueryDTO);
        return Result.success(pageVO);
    }


    @PutMapping("/update")
    public Result<ItemVO> updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO) {
        log.info("更新商品: {}", itemUpdateDTO);
        Item item = new Item();
        BeanUtils.copyProperties(itemUpdateDTO, item);
        log.info("{}", item);
        itemService.updateById(item);
        return Result.success("商品更新成功");
    }




}
