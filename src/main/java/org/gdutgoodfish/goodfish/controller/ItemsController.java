package org.gdutgoodfish.goodfish.controller;

import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.entity.Result;
import org.gdutgoodfish.goodfish.dto.ItemsDTO;
import org.gdutgoodfish.goodfish.service.ItemsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-12
 */
@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemsService itemsService;

    @DeleteMapping("/{itemId}")
    public Result deleteItem(@PathVariable("itemId") Integer itemId) {
        itemsService.deleteItem(itemId);
        return Result.success("商品删除成功");
    }

    @PostMapping
    public Result addItems(@RequestBody ItemsDTO itemsDTO) {
        itemsService.addItems(itemsDTO);
        return Result.success("商品添加成功");
    }

    @PostMapping("/upload")
    public Result<String> uploadItem(@RequestParam("file") MultipartFile file) throws IOException {
        String path = itemsService.uploadFile(file);
        return Result.success(path, "图片上传成功");
    }

}
