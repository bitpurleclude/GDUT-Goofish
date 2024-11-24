package org.gdutgoodfish.goodfish.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.exception.BaseException;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.ItemAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.dto.ItemUpdateDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.IItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
        boolean success = itemService.removeByIds(ids);
        if (success) {
            return Result.success("商品删除成功");
        } else {
            return Result.error("商品删除失败");
        }
    }

    @PostMapping
    public Result<String> addItems(@RequestBody ItemAddDTO itemAddDTO) {
        // 对传入参数进行判空
        if (itemAddDTO.getCategoryId() == null ||
                itemAddDTO.getPrice() == null ||
                StringUtils.isBlank(itemAddDTO.getDescription()) ||
                StringUtils.isBlank(itemAddDTO.getImage()) ||
                StringUtils.isBlank(itemAddDTO.getLocation()) ||
                StringUtils.isBlank(itemAddDTO.getName())) {
            throw new BaseException("参数均不能为空");
        }
        // 校验参数
        if (itemAddDTO.getCategoryId() < 0) {
            throw new BaseException("categoryId不能为空");
        }
        if (itemAddDTO.getPrice().compareTo(BigDecimal.valueOf(0)) == -1) {
            throw new BaseException("价格不能小于0");
        }
        // 添加商品
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
        // 参数判断
        if (itemUpdateDTO.getPrice().compareTo(BigDecimal.valueOf(0)) == -1) {
            return Result.error("价格不能小于0");
        }
        if (itemUpdateDTO.getCategoryId() == null || itemUpdateDTO.getCategoryId() < 0) {
            return Result.error("categoryId不能为空");
        }
        log.info("更新商品: {}", itemUpdateDTO);
        Item item = new Item();
        BeanUtils.copyProperties(itemUpdateDTO, item);
        log.info("{}", item);
        boolean success = itemService.updateById(item);
        if (success) {
            return Result.success("商品更新成功");
        } else {
            return Result.error("商品更新失败");
        }
    }


    @GetMapping
    public Result<List<ItemVO>> getItems() {
        log.info("用户获取自己的商品");
        List<ItemVO> vos = itemService.getUserItems();
        return Result.success(vos);
    }


}
