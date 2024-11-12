package org.gdutgoodfish.goodfish.service;

import org.gdutgoodfish.goodfish.dto.ItemsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemsService {
    void deleteItem(Integer itemId);

    void addItems(ItemsDTO itemsDTO);

    String uploadFile(MultipartFile file) throws IOException;
}
