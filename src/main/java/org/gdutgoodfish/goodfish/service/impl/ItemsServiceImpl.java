package org.gdutgoodfish.goodfish.service.impl;

import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.pojo.entity.Items;
import org.gdutgoodfish.goodfish.pojo.dto.ItemsDTO;
import org.gdutgoodfish.goodfish.mapper.ItemsMapper;
import org.gdutgoodfish.goodfish.service.ItemsService;
import org.gdutgoodfish.goodfish.util.FileUploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemsService {

    private final ItemsMapper itemsMapper;

    private final FileUploadUtil fileUploadUtil;

    @Override
    public void deleteItem(Integer itemId) {
        // TODO 获取用户id
        Integer userId = 1;
        itemsMapper.deleteById(userId, itemId);
    }

    @Override
    public void addItems(ItemsDTO itemsDTO) {
        // TODO 获取用户id
        Integer userId = 1;
        Items items = new Items();
        BeanUtils.copyProperties(itemsDTO, items);
        items.setUserId(userId);
        items.setPostedDate(LocalDateTime.now());
        itemsMapper.insert(items);
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;
        return fileUploadUtil.uploadFileToOss(file.getInputStream(), fileName);
    }


}
