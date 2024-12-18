package org.gdutgoodfish.goodfish.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import org.gdutgoodfish.goodfish.constant.PageConstant;
import org.gdutgoodfish.goodfish.exception.ItemException.ItemNotExistException;
import org.gdutgoodfish.goodfish.mapper.ItemMapper;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.dto.ItemPageQueryDTO;
import org.gdutgoodfish.goodfish.pojo.entity.Item;
import org.gdutgoodfish.goodfish.pojo.entity.Category;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.pojo.vo.ItemVO;
import org.gdutgoodfish.goodfish.pojo.vo.PageQueryVO;
import org.gdutgoodfish.goodfish.service.IItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public ItemVO getItem(Long itemId) {
        Item item = getById(itemId);
        if (item == null) {
            throw new ItemNotExistException("商品不存在");
        }
        Long userId = item.getUserId();
        Users users = Db.lambdaQuery(Users.class).eq(Users::getId, userId).one();
        Long categoryId = item.getCategoryId();
        Category category = Db.lambdaQuery(Category.class).eq(Category::getId, categoryId).one();
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(item, itemVO);
        itemVO.setUsername(users.getUsername());
        itemVO.setUserId(userId);
        itemVO.setCategoryName(category.getCategoryName());
        itemVO.setCategoryId(categoryId);
        return itemVO;
    }

    @Override
    public PageQueryVO<ItemVO> pageQuery(ItemPageQueryDTO itemPageQueryDTO) {
        // 判断是否传入分页条件，没有传入的话用默认分页条件
        if (itemPageQueryDTO.getPage() == null) {
            itemPageQueryDTO.setPage(Long.valueOf(PageConstant.DEFAULT_PAGE));
        }
        if (itemPageQueryDTO.getPageSize() == null) {
            itemPageQueryDTO.setPageSize(Long.valueOf(PageConstant.DEFAULT_PAGE_SIZE));
        }
        IPage<ItemVO> iPage = baseMapper.pageQuery(Page.of(itemPageQueryDTO.getPage(), itemPageQueryDTO.getPageSize()), itemPageQueryDTO);
        return new PageQueryVO<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public List<ItemVO> getUserItems() {
        return baseMapper.selectUserItems(UserContext.getCurrentId());
    }
}
