package org.gdutgoodfish.goodfish.service.impl;

import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.pojo.dto.ItemAddDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.mapper.ItemMapper;
import org.gdutgoodfish.goodfish.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gdutgoodfish.goodfish.util.FileUploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author J
 * @since 2024-11-14
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {


}
